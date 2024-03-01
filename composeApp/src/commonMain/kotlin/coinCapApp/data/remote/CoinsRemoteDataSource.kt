package coinCapApp.data.remote

import coinCapApp.data.models.CoinCapResponse

class CoinsRemoteDataSource(
    private val coinApi: CoinCapApi,
) {

    suspend fun getCoinsResponse(): CoinCapResponse {
        return try {
            coinApi.getCoins()
        } catch (e: Exception) {
            CoinCapResponse(emptyList())
        }
    }
}