package suzy.com.networkviewstatemachinekotlin.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import suzy.com.networkviewstatemachinekotlin.apod.ApodModel

interface NasaApi {

    @GET("planetary/apod")
    fun getApod(): Observable<ApodModel>

    //Date YYYY-MM-DD
    @GET("planetary/apod")
    fun getApodWithDate(@Query("date") date: String): Observable<ApodModel>

}