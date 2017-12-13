package suzy.com.networkviewstatemachinekotlin

import org.junit.Assert
import org.junit.Test
import suzy.com.networkviewstatemachinekotlin.apod.ApodModel
import suzy.com.networkviewstatemachinekotlin.apod.ApodViewModel

class ApodViewModelTest {

    @Test
    fun copyright_Null() {
        val apodModel = ApodModel("2017-12-10", "blabla", "hdurl", "image", "1", "title", "url", null)
        val apodViewModel = ApodViewModel(apodModel)

        val expected = null
        val actual = apodViewModel.copyright()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun copyright_NotNull() {
        val apodModel = ApodModel("2017-12-10", "blabla", "hdurl", "image", "1", "title", "url", "Suzy Paeta")
        val apodViewModel = ApodViewModel(apodModel)

        val expected = "Copyright: Suzy Paeta"
        val actual = apodViewModel.copyright()

        Assert.assertEquals(expected, actual)
    }
}