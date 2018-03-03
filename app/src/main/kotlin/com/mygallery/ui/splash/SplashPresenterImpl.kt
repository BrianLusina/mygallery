package com.mygallery.ui.splash

import com.mygallery.data.DataManager
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */

class SplashPresenterImpl<V : SplashView>
@Inject
constructor(dataManager: DataManager,
            compositeDisposable: CompositeDisposable,
            schedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(dataManager, schedulerProvider, compositeDisposable), SplashPresenter<V> {

    override fun onResume() {
        baseView.checkForPermissionToExternalStorage()
    }

    override fun onLoadImages() {
        val albumList = dataManager.getListOfVideoFolders()
        albumList.addAll(dataManager.getAllImagePaths())
        baseView.openMainActivity(albumList)
    }

    override fun onRequestForStoragePermission() {
        baseView.requestForStoragePermission()
    }

    override fun onStoragePermissionDenied() {
        baseView.displayPermissionRationale()
    }
}

