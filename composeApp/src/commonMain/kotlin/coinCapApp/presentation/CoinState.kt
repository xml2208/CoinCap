package coinCapApp.presentation

import coinCapApp.data.models.CoinItem

sealed class CoinState {
        data object Loading: CoinState()
        data class Success(val coinList: List<CoinItem>): CoinState()
        data class Error(val errorMessage: String): CoinState()
    
}
