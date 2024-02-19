package coinCapApp.presentation.coinCapApp.domain

import coinCapApp.presentation.coinCapApp.data.CoinRepository

class GetCoinsUseCase(
    private val coinRepository: CoinRepository
) {
    
    suspend operator fun invoke() =
        coinRepository.getAllCoins()
}