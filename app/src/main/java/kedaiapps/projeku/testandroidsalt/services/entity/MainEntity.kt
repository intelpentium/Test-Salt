package kedaiapps.projeku.testandroidsalt.services.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

//home
@Keep
data class ResponseHome(
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String,
)


