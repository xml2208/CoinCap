package coinCapApp.presentation.screens

import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coinCapApp.data.models.CoinItem
import coinCapApp.presentation.CoinState
import coinCapApp.presentation.CoinViewModel
import coinCapApp.presentation.composables.CoinItem
import coinCapApp.presentation.composables.SearchUi

class CoinListScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { CoinViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val query = screenModel.query.collectAsState().value

        LaunchedEffect("key1") {
            screenModel.getCoins()
        }

        when (val state = screenModel.state.collectAsState().value) {
            is CoinState.Loading -> LoadingUi()
            is CoinState.Success -> {
                SuccessUi(
                    query = query,
                    onValueChange = screenModel::onSearchChange,
                    coins = state.coinList,
                    onClick = { item ->
                        navigator.push(CoinDetailScreen(coinItem = item, onBack = { navigator.pop() }))
                    }
                )
            }
            else -> Unit
        }
    }

    @Composable
    fun SuccessUi(
        query: String,
        coins: List<CoinItem>,
        onClick: (CoinItem) -> Unit,
        onValueChange: (String) -> Unit,
        ) {
        Column {
            SearchUi(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                query = query,
                onValueChange = onValueChange
            )
            LazyColumn {
                items(coins) { coin ->
                    CoinItem(
                        coinItem = coin,
                        onClick = { onClick(coin) }
                    )
                }
            }
        }
    }

    @Composable
    fun LoadingUi() {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


