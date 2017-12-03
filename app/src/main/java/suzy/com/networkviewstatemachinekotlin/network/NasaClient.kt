package suzy.com.networkviewstatemachinekotlin.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NasaClient : IClient {

    private val NASA_ENDPOINT = "https://api.nasa.gov/"
    private val NASA_API_KEY = "DEMO_KEY"

    private val nasaApi: NasaApi

    init {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(NasaRequestInterceptor(NASA_API_KEY))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(NASA_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        nasaApi = retrofit.create(NasaApi::class.java)
    }

    override fun getApi() : NasaApi = nasaApi

    class NasaRequestInterceptor(private val apiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val url = request.url().newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()

            val newRequest = request.newBuilder()
                    .url(url)
                    .build()

            return chain.proceed(newRequest)
        }
    }
}

