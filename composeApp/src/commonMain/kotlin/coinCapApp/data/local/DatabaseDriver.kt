package coinCapApp.presentation.coinCapApp.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriver {
    fun createDriver(): SqlDriver
}