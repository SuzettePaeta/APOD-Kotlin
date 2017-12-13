package suzy.com.networkviewstatemachinekotlin.apod

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
        apodModel.copyright?.let {
            return "Copyright: $it"
        }
        return null
    }
}