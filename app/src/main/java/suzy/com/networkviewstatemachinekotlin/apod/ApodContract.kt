package suzy.com.networkviewstatemachinekotlin.apod

import suzy.com.networkviewstatemachinekotlin.NetworkingViewState

interface ApodContract {
    interface View {
        var networkingViewState: NetworkingViewState
    }

    interface Presenter {
        fun getApod()
    }
}