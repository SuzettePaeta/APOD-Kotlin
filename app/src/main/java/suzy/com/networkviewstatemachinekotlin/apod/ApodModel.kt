package suzy.com.networkviewstatemachinekotlin.apod

// Astronomy Picture of the day
data class ApodModel(val date: String,
           val explanation: String,
           val hdurl: String,
           val media_type: String,
           val service_version: String,
           val title: String,
           val url: String,
           val copyright: String?)

enum class MediaType(val type: String) {
    IMAGE("image"),
    VIDEO("video")
}