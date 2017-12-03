package suzy.com.networkviewstatemachinekotlin.apod

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_apod.*
import suzy.com.networkviewstatemachinekotlin.general.BaseActivity
import suzy.com.networkviewstatemachinekotlin.NetworkingViewState
import suzy.com.networkviewstatemachinekotlin.R
import suzy.com.networkviewstatemachinekotlin.network.NasaClient
import kotlin.properties.Delegates

class ApodView : BaseActivity(), ApodContract.View {

    private val presenter = ApodPresenter(this, NasaClient, this)
    private lateinit var imageHolder: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod)
        supportActionBar?.title = getString(R.string.title_apod)
        imageHolder = findViewById(R.id.apod_image)
        presenter.getApod()
    }

    override var networkingViewState: NetworkingViewState by Delegates.observable<NetworkingViewState>(NetworkingViewState.Init(),
            { property, oldValue, newValue ->
                when (newValue) {
                    is NetworkingViewState.Loading -> {

                    }
                    is NetworkingViewState.Success<*> -> {
                        val item = newValue.item as? ApodViewModel
                        item?.let { apod ->
                            apod_title.text = apod.title()
                            apod_explanation.text = apod.explanation()
                            apod.copyright()?.let {
                                apod_copyright.visibility = VISIBLE
                                apod_copyright.text = it
                            }
                            Picasso.with(this).load(apod.url()).into(imageHolder)
                        }
                    }
                    is NetworkingViewState.Error -> {

                    }
                }
            })
}