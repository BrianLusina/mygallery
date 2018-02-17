package com.mygallery.ui.singlealbum

import android.os.Bundle
import com.mygallery.ui.base.BaseActivity
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes This will display a Single Albums photos/videos
 */
class SingleAlbumActivity : BaseActivity(), SingleAlbumView {

    @Inject
    lateinit var singleAlbumPresenter: SingleAlbumPresenter<SingleAlbumView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: set content view
        // e.g. setContentView(R.layout.activity_audio_detail)

        // inject dependencies into this activity
        activityComponent.injectSingleAlbumActivity(this)

        // attach your presenter
        singleAlbumPresenter.onAttach(this)
    }
}