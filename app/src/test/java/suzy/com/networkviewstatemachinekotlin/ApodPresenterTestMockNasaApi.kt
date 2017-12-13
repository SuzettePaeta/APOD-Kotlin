package suzy.com.networkviewstatemachinekotlin

import android.content.Context
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import suzy.com.networkviewstatemachinekotlin.apod.ApodContract
import suzy.com.networkviewstatemachinekotlin.apod.ApodModel
import suzy.com.networkviewstatemachinekotlin.apod.ApodPresenter
import suzy.com.networkviewstatemachinekotlin.network.NasaApi

class ApodPresenterTestMockNasaApi {

    val context = mock<Context>()
    val apodView = mock<ApodContract.View>()
    val mockNasaApi = mock<NasaApi>()

    @get:Rule
    val rule = ImmediateSchedulerRule()

    @Test
    fun getApod_Success() {
        val fakeApodModel = ApodModel("2017-12-10", "blabla", "hdurl", "image", "1", "title", "url", "Suzy Paeta")
        whenever(mockNasaApi.getApod()).thenReturn(Observable.just(fakeApodModel))
        val presenter = ApodPresenter(apodView, mockNasaApi, context)

        presenter.getApod()

        verify(apodView).networkingViewState = isA<NetworkingViewState.Success<ApodModel>>()
        verifyNoMoreInteractions(apodView)
    }

    @Test
    fun getApod_Failure() {
        whenever(mockNasaApi.getApod()).thenReturn(Observable.error<ApodModel>(Throwable("An error as occurred")))
        val presenter = ApodPresenter(apodView, mockNasaApi, context)

        presenter.getApod()

        verify(apodView).networkingViewState = isA<NetworkingViewState.Error>()
        verifyNoMoreInteractions(apodView)
    }
}