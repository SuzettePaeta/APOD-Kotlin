package suzy.com.networkviewstatemachinekotlin.apod

import android.content.Context

class ApodViewModel(val apodModel: ApodModel) {

    fun title(): String {
        return apodModel.title
    }

    fun url(): String {
        return apodModel.url
    }

    fun explanation(): String {
        return apodModel.explanation
    }

    fun copyright() : String? {
        if(apodModel.copyright != null) {
            return "Copyright: ${apodModel.copyright}"
        }
        return null
    }
}