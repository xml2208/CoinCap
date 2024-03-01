package coinCapApp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import coinCapApp.data.models.CoinItem
import coinCapApp.presentation.coinCapApp.domain.GetCoinsUseCase
import coinCapApp.presentation.coinCapApp.domain.InsertCoinsUseCase
import io.github.aakira.napier.Napier

class CoinViewModel(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val insertCoinsUseCase: InsertCoinsUseCase,
) : StateScreenModel<CoinState>(CoinState.Loading) {

    private val _query = MutableStateFlow("")
    val query = _query
    private var allCoins by mutableStateOf<List<CoinItem>>(emptyList())

    suspend fun insertList() = insertCoinsUseCase()

    suspend fun getCoins() {
        try {
            getCoinsUseCase.invoke()?.collect { localData ->
                Napier.d(message = "LocalDataList size: ${localData.size}", tag = "xml2208")
                allCoins = localData
                mutableState.value = CoinState.Success(coinList = allCoins)
            }
        } catch (e: Exception) {
            Napier.e(message = e.toString(), tag = "xml2208")
            mutableState.value = CoinState.Error(errorMessage = e.message.toString())
        }
    }

    private fun executeSearch(): List<CoinItem> = allCoins.filter { it.checkMatching(_query.value) }

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