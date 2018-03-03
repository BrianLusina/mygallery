package com.mygallery.ui.singlealbum

import android.os.Bundle
import com.mygallery.R
import com.mygallery.ui.base.BaseActivity
import com.mygallery.ui.singlealbum.grid.GridFragment
import com.mygallery.utils.BUNDLE_KEY_CURRENT_POSITION
import com.mygallery.utils.INTENT_KEY_ALBUM_FOLDER_NAME
import com.mygallery.utils.INTENT_KEY_ALBUM_IS_VIDEO
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes This will display a Single Albums photos/videos
 */
class SingleAlbumActivity : BaseActivity(), SingleAlbumView {

    @Inject
    lateinit var singleAlbumPresenter: SingleAlbumPresenter<SingleAlbumView>

    companion object {
        const val KEY_SINGLE_ALBUM_FOLDER_NAME = "KEY_SINGLE_ALBUM_FOLDER_NAME"
        const val KEY_SINGLE_ALBUM_IS_VIDEO = "KEY_SINGLE_ALBUM_IS_VIDEO"
        const val KEY_CURRENT_POSITION = "KEY_CURRENT_POSITION"
        var currentPosition: Int = 0
    }

    var folderName = ""
    var isVideo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_album)

        // inject dependencies into this activity
        activityComponent.injectSingleAlbumActivity(this)

        // attach your presenter
        singleAlbumPresenter.onAttach(this)

        if (savedInstanceState != null) {
            folderName = savedInstanceState.getString(KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = savedInstanceState.getBoolean(KEY_SINGLE_ALBUM_IS_VIDEO)
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
            // return here to avoid adding additional Grid Fragments when changing orientation
            return
        }

        singleAlbumPresenter.onCreateView()
    }

    override fun onResume() {
        super.onResume()
        singleAlbumPresenter.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_SINGLE_ALBUM_FOLDER_NAME, folderName)
        outState?.putBoolean(KEY_SINGLE_ALBUM_IS_VIDEO, isVideo)
        outState?.putInt(KEY_CURRENT_POSITION, currentPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            folderName = savedInstanceState.getString(KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = savedInstanceState.getBoolean(KEY_SINGLE_ALBUM_IS_VIDEO)
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
        }
    }

    override fun setupFragment() {
        val fragmentManager = supportFragmentManager

        // pass the current position to the Fragment along with the bundle data
        val bundle = Bundle()
        bundle.putInt(BUNDLE_KEY_CURRENT_POSITION, currentPosition)
        bundle.putBoolean(INTENT_KEY_ALBUM_IS_VIDEO, isVideo)
        bundle.putString(INTENT_KEY_ALBUM_FOLDER_NAME, folderName)
        val gridFragment = GridFragment()
        gridFragment.arguments = bundle

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, gridFragment, GridFragment::class.simpleName)
                .commit()
    }

    override fun retrieveBundleFromIntent() {
        if (intent.extras != null || intent.getBundleExtra(INTENT_KEY_ALBUM_FOLDER_NAME) != null) {
            folderName = intent.extras.getString(INTENT_KEY_ALBUM_FOLDER_NAME)
            isVideo = intent.extras.getBoolean(INTENT_KEY_ALBUM_IS_VIDEO)
        }
    }
}
