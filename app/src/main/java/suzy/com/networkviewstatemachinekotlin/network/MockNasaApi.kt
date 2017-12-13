package suzy.com.networkviewstatemachinekotlin.network

import io.reactivex.Observable
import suzy.com.networkviewstatemachinekotlin.apod.ApodModel

class MockNasaApiSuccess: NasaApi {

    override fun getApodWithDate(date: String): Observable<ApodModel> {
        val fakeApodModel = ApodModel("2017-12-10", "Did you ever get caught in a meteor shower?","hdurl","image","1", "title", "url", "Suzy Paeta")
        return Observable.just(fakeApodModel)
    }

    override fun getApod(): Observable<ApodModel> {
        val fakeApodModel = ApodModel("2017-12-10", "Did you ever get caught in a meteor shower?","hdurl","image","1", "title", "url", "Suzy Paeta")
        return Observable.just(fakeApodModel)
    }

}

class MockNasaApiFailure: NasaApi {

    override fun getApodWithDate(date: String): Observable<ApodModel> {
        return Observable.error<ApodModel>(Throwable("An error as occurred"))
    }

    override fun getApod(): Observable<ApodModel> {
        return Observable.error<ApodModel>(Throwable("An error as occurred"))
    }

}