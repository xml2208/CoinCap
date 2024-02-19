package coinCapApp.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class CoinsRemoteDataSource(
    private val coinApi: CoinCapApi,
) {

    suspend fun getCoinList() =
        withContext(Dispatchers.IO) {
            coinApi.getCoins()
        }

}