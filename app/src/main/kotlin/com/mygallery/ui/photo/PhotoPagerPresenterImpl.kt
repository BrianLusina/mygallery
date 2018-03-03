package com.mygallery.ui.photo

import com.mygallery.data.DataManager
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */

class PhotoPagerPresenterImpl<V : PhotoPagerView>
@Inject
constructor(dataManager: DataManager,
            compositeDisposable: CompositeDisposable,
            schedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(dataManager, schedulerProvider, compositeDisposable), PhotoPagerPresenter<V> {

    override fun onPrepareSharedElementTransition() {
        baseView.prepareSharedElementTransition()
    }

    override fun onRetrieveBundle() {
        baseView.retrieveBundleFromArguments()
    }

}
