package coinCapApp.presentation.coinCapApp.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import MyApplication

actual class DatabaseDriver {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = Database.Schema.synchronous(),
            context = MyApplication().applicationContext,
            name = "CoinCapDatabase"
        )
    }
}
