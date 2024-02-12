package coinCapApp.data.remote

import coinCapApp.data.models.CoinCapResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class CryptoApi(private val client: HttpClient) {	
    suspend fun getCoins(): CoinCapResponse = client.get(HttpRoutes.GET_ASSETS).body<CoinCapResponse>()
}

object HttpRoutes {
    private const val BASE_URL = "https://api.coincap.io/v2"
    const val GET_ASSETS = "$BASE_URL/assets"
    const val GET_ICONS = "https://coinicons-api.vercel.app/api/icon/"
}