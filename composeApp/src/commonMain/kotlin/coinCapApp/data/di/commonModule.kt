package coinCapApp.presentation.coinCapApp.data.di

import coinCapApp.data.remote.CoinCapApi
import coinCapApp.data.local.CoinsLocalDataStore
import coinCapApp.data.remote.CoinsRemoteDataSource
import coinCapApp.presentation.coinCapApp.data.CoinRepository
import coinCapApp.presentation.coinCapApp.domain.GetCoinsUseCase
import coinCapApp.presentation.coinCapApp.domain.InsertCoinsUseCase
import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf

val commonModule = module {

    single {
        val client = HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }

                }
            }
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        CoinCapApi(client = client)
    }

    single {
        CoinsRemoteDataSource(coinApi = get())
    }

    singleOf(::CoinsLocalDataStore)
    
    singleOf(::CoinRepository)

    singleOf(::GetCoinsUseCase)
    
    singleOf(::InsertCoinsUseCase)
    
}