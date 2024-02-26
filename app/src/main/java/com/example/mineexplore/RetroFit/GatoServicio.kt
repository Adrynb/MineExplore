import retrofit2.Response
import retrofit2.Call
import retrofit2.http.GET

interface GatoServicio {
    @GET("images/search")
    suspend fun getRandomCatImage(): List<Gato>
}
