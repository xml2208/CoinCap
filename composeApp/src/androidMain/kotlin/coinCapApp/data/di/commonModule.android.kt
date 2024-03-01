package coinCapApp.presentation.coinCapApp.data.di

import coinCapApp.presentation.coinCapApp.data.local.DatabaseDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single {
        DatabaseDriver()
    }
}