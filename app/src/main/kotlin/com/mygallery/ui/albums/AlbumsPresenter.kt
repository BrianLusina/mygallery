package com.mygallery.ui.albums

import com.mygallery.ui.base.BasePresenter

interface AlbumsPresenter<V : AlbumsView> : BasePresenter<V> {

    /**
     * Retrieve Bundle from intent
     * */
    fun onRetrieveBundle()

    /**
     * Setup Views
     * */
    fun onResume()

    /**
     * on Drawer closed callback
     * */
    fun onDrawerClosed()

    /**
     * Callback to track closing of drawer
     * */
    fun onDrawerOpened()

    /**
     * On Launch camera clicked*/
    fun onLaunchCameraClicked()

    /**
     * On open source menu item clicked
     * */
    fun onOpenSourceMenuClicked()
}