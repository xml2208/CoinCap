package coinCapApp.presentation.coinCapApp.data

import coinCapApp.data.local.CoinsLocalDataStore
import coinCapApp.data.remote.CoinsRemoteDataSource

class CoinRepository(
    private val coinRemoteDataSource: CoinsRemoteDataSource,
//    private val coinLocalDataStore: CoinsLocalDataStore
) {
    
     suspend fun getAllCoins() = coinRemoteDataSource.getCoinList().data
}