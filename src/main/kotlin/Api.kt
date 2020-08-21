import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by t.coulange on 21/08/2020.
 */
val API_URL: String? = "https://api.github.com"

@Serializable
class Contributor(val login: String?, val contributions: Int)

interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun contributors(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?
    ): List<Contributor>

    @GET("/fakepath")
    suspend fun fakePathThatThrows404()
}
