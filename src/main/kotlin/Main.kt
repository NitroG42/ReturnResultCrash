import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

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

fun main() {
    val apiHolder = ApiHolder()
    // Create a call instance for looking up Retrofit contributors.
    runBlocking {
        kotlin.runCatching {
            github.fakePathThatThrows404()
        }.onFailure {
            println("will be displayed: error caught")
        }

        apiHolder.returnResultFailureDirectly().onFailure {
            println("will be displayed: error caught")
        }

        apiHolder.callWSAndReturnFailure().onFailure {
            println("won't be displayed: crash")
        }
    }

}