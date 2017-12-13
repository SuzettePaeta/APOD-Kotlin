package suzy.com.networkviewstatemachinekotlin.apod

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import suzy.com.networkviewstatemachinekotlin.NetworkingViewState
import suzy.com.networkviewstatemachinekotlin.network.NasaApi

class ApodPresenter(val apodView: ApodContract.View, val client: NasaApi, val context: Context) : ApodContract.Presenter {

    override fun getApod() {
        client.getApod()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { apod: ApodModel ->
                            apodView.networkingViewState = NetworkingViewState.Success(ApodViewModel(apod))
                        },
                        { error ->
                            apodView.networkingViewState = NetworkingViewState.Error(error.message)
                        })
    }
}