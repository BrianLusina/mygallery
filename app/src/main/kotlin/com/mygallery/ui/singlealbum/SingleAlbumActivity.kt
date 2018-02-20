package com.mygallery.ui.singlealbum

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.mygallery.R
import com.mygallery.ui.base.BaseActivity
import com.mygallery.ui.photo.PhotoActivity
import com.mygallery.utils.INTENT_KEY_PHOTO_ITEM_PATH
import com.mygallery.utils.INTENT_KEY_SINGLE_ALBUM_FOLDER_NAME
import com.mygallery.utils.INTENT_KEY_SINGLE_ALBUM_IS_VIDEO
import kotlinx.android.synthetic.main.activity_single_album.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes This will display a Single Albums photos/videos
 */
class SingleAlbumActivity : BaseActivity(), SingleAlbumView, SingleAlbumRecyclerAdapter.Callback {

    @Inject
    lateinit var singleAlbumPresenter: SingleAlbumPresenter<SingleAlbumView>

    @Inject
    lateinit var singleAlbumRecyclerAdapter: SingleAlbumRecyclerAdapter

    companion object {
        const val KEY_SINGLE_ALBUM_FOLDER_NAME = "KEY_SINGLE_ALBUM_FOLDER_NAME"
        const val KEY_SINGLE_ALBUM_IS_VIDEO = "KEY_SINGLE_ALBUM_IS_VIDEO"
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

        singleAlbumRecyclerAdapter.callback = this

        if (savedInstanceState != null) {
            folderName = savedInstanceState.getString(KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = savedInstanceState.getBoolean(KEY_SINGLE_ALBUM_IS_VIDEO)
        } else {
            singleAlbumPresenter.onRetrieveBundle()
        }
    }

    override fun onResume() {
        super.onResume()
        singleAlbumPresenter.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_SINGLE_ALBUM_FOLDER_NAME, folderName)
        outState?.putBoolean(KEY_SINGLE_ALBUM_IS_VIDEO, isVideo)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            folderName = savedInstanceState.getString(KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = savedInstanceState.getBoolean(KEY_SINGLE_ALBUM_IS_VIDEO)
        }
    }

    override fun setupRecyclerAdapter() {
        recycler_view_single_album.layoutManager = GridLayoutManager(this, 2)
        recycler_view_single_album.setHasFixedSize(true)
        recycler_view_single_album.adapter = singleAlbumRecyclerAdapter

        singleAlbumPresenter.onRecyclerViewSetup(folderName, isVideo)
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = folderName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun retrieveBundleFromIntent() {
        if (intent.extras != null || intent.getBundleExtra(INTENT_KEY_SINGLE_ALBUM_FOLDER_NAME) != null) {
            folderName = intent.extras.getString(INTENT_KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = intent.extras.getBoolean(INTENT_KEY_SINGLE_ALBUM_IS_VIDEO)
        }
    }

    override fun addItemsToAdapter(imageList: ArrayList<String>) {
        singleAlbumRecyclerAdapter.addItemsUsingDiff(imageList)
    }

    override fun onSinglePhotoClick(photoItemName: String) {
        startActivity<PhotoActivity>(INTENT_KEY_PHOTO_ITEM_PATH to photoItemName)
    }
}
