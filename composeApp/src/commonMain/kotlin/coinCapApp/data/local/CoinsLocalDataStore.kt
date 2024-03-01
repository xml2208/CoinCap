package coinCapApp.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import coinCapApp.data.models.CoinItem
import coinCapApp.data.models.toDomain
import coinCapApp.data.models.toUiItem
import coinCapApp.presentation.coinCapApp.data.local.DatabaseDriver
import com.xml.example.db.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinsLocalDataStore(databaseFactory: DatabaseDriver) {
    private val database = Database(databaseFactory.createDriver())

    suspend fun insertCoinList(coins: List<CoinItem>) {
        database.coinListQueries.transaction {
            coins.forEach { coinItem ->
                database.coinListQueries.insertCoinList(coinEntity = coinItem.toDomain())
            }
        }
    }

    fun getAllCoins(): Flow<List<CoinItem>> =
        database.coinListQueries.getCoinList()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toUiItem()
                }
            }

}