package com.mygallery.ui.splash

import com.mygallery.ui.base.BasePresenter

interface SplashPresenter<V : SplashView> : BasePresenter<V> {

    fun onResume()

    /**
     * Load images callback
     * */
    fun onLoadImages()

    /**
     * Callback to request for storage permission. This will delegate this task to the view to display
     * the ui for the user to interact with
     * */
    fun onRequestForStoragePermission()

    /**
     * On Storage permission denied callback. will be used to display a rationale as to why the storage permission
     * is necessary
     * */
    fun onStoragePermissionDenied()
}
