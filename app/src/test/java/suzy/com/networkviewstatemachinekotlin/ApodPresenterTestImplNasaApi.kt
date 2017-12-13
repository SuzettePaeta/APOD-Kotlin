package suzy.com.networkviewstatemachinekotlin

import android.content.Context
import com.nhaarman.mockito_kotlin.*
import org.junit.Rule
import org.junit.Test
import suzy.com.networkviewstatemachinekotlin.apod.ApodContract
import suzy.com.networkviewstatemachinekotlin.apod.ApodModel
import suzy.com.networkviewstatemachinekotlin.apod.ApodPresenter
import suzy.com.networkviewstatemachinekotlin.network.MockNasaApiFailure
import suzy.com.networkviewstatemachinekotlin.network.MockNasaApiSuccess

class ApodPresenterTestImplNasaApi {

    val context = mock<Context>()
    val apodView = mock<ApodContract.View>()

    @get:Rule
    val rule = ImmediateSchedulerRule()

    @Test
    fun getApod_Success() {
        val nasaApi = MockNasaApiSuccess()
        val presenter = ApodPresenter(apodView, nasaApi, context)

        presenter.getApod()

        verify(apodView).networkingViewState = isA<NetworkingViewState.Success<ApodModel>>()
        verifyNoMoreInteractions(apodView)
    }

    @Test
    fun getApod_Failure() {
        val nasaApi = MockNasaApiFailure()
        val presenter = ApodPresenter(apodView, nasaApi, context)

        presenter.getApod()

        verify(apodView).networkingViewState = isA<NetworkingViewState.Error>()
        verifyNoMoreInteractions(apodView)
    }
}