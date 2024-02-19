package coinCapApp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import coinCapApp.data.remote.CryptoApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import coinCapApp.data.models.CoinItem
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class CoinViewModel : StateScreenModel<CoinState>(CoinState.Loading) {

    private val client = HttpClient {
        install(Logging)
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    private val api = CryptoApi(client = client)
    private val _query = MutableStateFlow("")
    val query = _query

    private var allCoins by mutableStateOf<List<CoinItem>>(emptyList())


    suspend fun getCoins() {
        try {
            allCoins = api.getCoins().data
            mutableState.value = CoinState.Success(coinList = allCoins)
        } catch (e: Exception) {
            println(e.message)
            mutableState.value = CoinState.Error(errorMessage = e.message.toString())
        }
    }

    private suspend fun executeSearch(): List<CoinItem> = api.getCoins().data.filter { it.checkMatching(_query.value) }

    fun onSearchChange(text: String) {
        _query.value = text
        screenModelScope.launch {
            if (_query.value != "") {
                mutableState.value = CoinState.Success(coinList = executeSearch())
            } else {
                mutableState.value = CoinState.Success(coinList = allCoins)
            }
        }
    }

    fun topCoins() = allCoins.subList(0, 13)
    
}