package coinCapApp.presentation.coinCapApp.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.xml.example.db.Database

@Suppress("-Xexpect-actual-classes")
actual class DatabaseDriver {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = Database.Schema.synchronous(),
            name = "CoinCapDatabase"
        )
    }
} 