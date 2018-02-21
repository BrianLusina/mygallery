package com.mygallery.ui.singlealbum

import com.mygallery.ui.base.BaseView

interface SingleAlbumView : BaseView {

    /**
     * Setup Fragment
     * */
    fun setupFragment()

    /**
     * Receives Bundle from intent
     * */
    fun retrieveBundleFromIntent()
}
