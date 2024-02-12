package coinCapApp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinCapResponse(
    val data: List<CoinItem>
)

@Serializable
data class CoinItem(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
    val rank: String,
    val changePercent24Hr: Double		
) {

    fun checkMatching(query: String): Boolean {
        val combinations = listOf(
            name,"${name.first()}",
            )
        return combinations.any {
            query.contains(it, ignoreCase = true)
        }

    }
}

