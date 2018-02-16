package com.mygallery.ui.splash

import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseView

interface SplashView : BaseView {

    /**
     * Checks if we have user permission to access External storage
     * */
    fun checkForPermissionToExternalStorage()

    /**
     * Requests for storage permission
     * */
    fun requestForStoragePermission()

    /**
     * Displays Permission rationale
     * */
    fun displayPermissionRationale()

    /**
     * Opens Main Activity
     * */
    fun openMainActivity(albumList: ArrayList<AlbumModel>)
}