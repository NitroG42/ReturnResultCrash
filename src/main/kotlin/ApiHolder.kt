import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Created by t.coulange on 21/08/2020.
 */
class ApiHolder {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // Create an instance of our GitHub API interface.
    private val github by lazy { retrofit.create(GitHub::class.java) }

    suspend fun callErrorAndReturnResult() = kotlin.runCatching {
        github.fakePathThatThrows404()
    }

}