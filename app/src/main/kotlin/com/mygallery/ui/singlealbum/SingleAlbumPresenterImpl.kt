package com.mygallery.ui.singlealbum

import com.mygallery.data.DataManager
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */

class SingleAlbumPresenterImpl<V : SingleAlbumView>
@Inject
constructor(dataManager: DataManager,
            compositeDisposable: CompositeDisposable,
            schedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(dataManager, schedulerProvider, compositeDisposable), SingleAlbumPresenter<V> {

    override fun onResume() {
        baseView.setupToolbar()
        baseView.setupRecyclerAdapter()
    }

    override fun onRetrieveBundle() {
        baseView.retrieveBundleFromIntent()
    }

    override fun onRecyclerViewSetup(folderName: String, isVideo: Boolean) {
        val imageList = dataManager.getAllShownImagesPath(folderName, isVideo)
        baseView.addItemsToAdapter(imageList)
    }
}

