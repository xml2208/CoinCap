package coinCapApp.presentation.coinCapApp.data

import coinCapApp.data.local.CoinsLocalDataStore
import coinCapApp.data.models.CoinItem
import coinCapApp.data.remote.CoinsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CoinRepository(
    private val coinRemoteDataSource: CoinsRemoteDataSource,
    private val coinLocalDataStore: CoinsLocalDataStore
) {

    suspend fun insertAllCoins() {
        try {
            val coins = coinRemoteDataSource.getCoinsResponse().data
            coinLocalDataStore.insertCoinList(coins)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllCoins(): Flow<List<CoinItem>>? {
        return try {
            coinLocalDataStore.getAllCoins()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}