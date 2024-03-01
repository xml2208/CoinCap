package coinCapApp.presentation.coinCapApp.domain

import coinCapApp.presentation.coinCapApp.data.CoinRepository

class GetCoinsUseCase(
    private val coinRepository: CoinRepository
) {
    
    operator fun invoke() =
        coinRepository.getAllCoins()
}