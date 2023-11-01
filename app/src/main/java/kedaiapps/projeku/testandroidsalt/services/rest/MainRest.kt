package kedaiapps.projeku.testandroidsalt.services.rest

import kedaiapps.projeku.testandroidsalt.services.Response
import kedaiapps.projeku.testandroidsalt.services.entity.*
import retrofit2.http.*

interface MainRest {

    //home
    @GET("top-headlines")
    suspend fun home(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ) : Response<List<ResponseHome>>
}