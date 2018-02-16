package com.mygallery.ui.main

import com.mygallery.ui.base.BasePresenter

interface MainPresenter<V : MainView> : BasePresenter<V> {

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
}