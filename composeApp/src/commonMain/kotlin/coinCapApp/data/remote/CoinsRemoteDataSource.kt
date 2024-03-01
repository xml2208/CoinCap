package coinCapApp.data.remote

import coinCapApp.data.models.CoinCapResponse
import io.github.aakira.napier.Napier

class CoinsRemoteDataSource(
    private val coinApi: CoinCapApi,
) {

    suspend fun getCoinsResponse(): CoinCapResponse {
        return try {
            coinApi.getCoins()
        } catch (e: Exception) {
            Napier.e(e.message.toString())
            CoinCapResponse(emptyList())
        }
    }
    
}