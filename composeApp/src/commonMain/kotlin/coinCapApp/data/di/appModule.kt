package coinCapApp.presentation.coinCapApp.data.di

import app.cash.sqldelight.db.SqlDriver
import coinCapApp.data.remote.CoinCapApi
import coinCapApp.data.remote.CoinsRemoteDataSource
import coinCapApp.presentation.coinCapApp.data.CoinRepository
import coinCapApp.presentation.coinCapApp.domain.GetCoinsUseCase
import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf

val appModule = module {

    single {
        val client = HttpClient {
            install(Logging)
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        CoinCapApi(client = client)
    }

    singleOf(::CoinsRemoteDataSource)
    
    singleOf(::CoinRepository)
    
    singleOf(::GetCoinsUseCase)
    
//    single<SqlDriver> {
//        
//    }

}