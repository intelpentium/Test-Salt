package kedaiapps.projeku.testandroidsalt.modules.retrofit

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

@Keep
data class ErrorResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String
)
