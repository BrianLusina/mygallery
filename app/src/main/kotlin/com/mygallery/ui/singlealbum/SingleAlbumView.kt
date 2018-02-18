package com.mygallery.ui.singlealbum

import com.mygallery.ui.base.BaseView

interface SingleAlbumView : BaseView {

    /**
     * Sets up Toolbar
     * */
    fun setupToolbar()

    /**
     * Sets up Recycler Adapter
     * */
    fun setupRecyclerAdapter()

    /**
     * Receives Bundle from intent
     * */
    fun retrieveBundleFromIntent()

    /**
     * Adds Items to adapter
     * @param imageList Array List of images from the given folder path
     * */
    fun addItemsToAdapter(imageList : ArrayList<String>)
}