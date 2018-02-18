package com.mygallery.ui.photo

import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateInterpolator
import com.bumptech.glide.Glide
import com.mygallery.R
import com.mygallery.ui.base.BaseActivity
import com.mygallery.utils.INTENT_KEY_PHOTO_ITEM_PATH
import kotlinx.android.synthetic.main.activity_photo.*
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes This displays a single Photo when clicked
 */
class PhotoActivity : BaseActivity(), PhotoView {

    @Inject
    lateinit var photoPresenter: PhotoPresenter<PhotoView>

    var photoPath = ""
    var isAppBarShown = true

    companion object {
        const val KEY_PHOTO_PATH = "KEY_PHOTO_PATH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        // inject dependencies into this activity
        activityComponent.injectPhotoActivity(this)

        // attach your presenter
        photoPresenter.onAttach(this)

        if (savedInstanceState != null){
            photoPath = savedInstanceState.getString(KEY_PHOTO_PATH)
        } else{
            photoPresenter.onRetrieveBundle()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_PHOTO_PATH, photoPath)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null){
            photoPath = savedInstanceState.getString(KEY_PHOTO_PATH)
        }
    }

    override fun onResume() {
        super.onResume()
        photoPresenter.onResume()
    }

    override fun retrieveBundleFromIntent() {
        if(intent.extras != null && intent.getStringExtra(INTENT_KEY_PHOTO_ITEM_PATH) != null){
            photoPath = intent.extras.getString(INTENT_KEY_PHOTO_ITEM_PATH)
        }
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
        // Enable the Up button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    override fun setupFullScreenImage() {
        Glide.with(this)
                .load(photoPath)
                .into(image_view_full_screen)

        Handler().postDelayed({
            if (supportActionBar != null)
                appbar.animate().translationY(-appbar.bottom.toFloat()).setInterpolator(AccelerateInterpolator()).start()
            isAppBarShown = false
        }, 1500)
    }
}