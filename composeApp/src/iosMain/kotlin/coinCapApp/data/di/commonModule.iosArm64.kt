package coinCapApp.presentation.coinCapApp.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import coinCapApp.presentation.coinCapApp.data.local.DatabaseDriver

actual fun platformModule(): Module = module {
    single {
        DatabaseDriver()
    }
}