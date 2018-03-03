package com.mygallery.ui.albums

import com.mygallery.data.DataManager
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */

class AlbumsPresenterImpl<V : AlbumsView>
@Inject
constructor(dataManager: DataManager,
            compositeDisposable: CompositeDisposable,
            schedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(dataManager, schedulerProvider, compositeDisposable), AlbumsPresenter<V> {

    override fun onAttach(mBaseView: V) {
        super.onAttach(mBaseView)
    }

    override fun onResume() {
        baseView.setUpToolbar()
        baseView.setupNavigationView()
        baseView.setupDrawerLayout()
        baseView.setupRecyclerAdapter()
    }

    override fun onRetrieveBundle() {
        baseView.retrieveBundleFromIntent()
    }

    override fun onDrawerClosed() {
        baseView.setupMenuIconOnDrawer()
    }

    override fun onDrawerOpened() {
        baseView.setupBackIconOnDrawer()
    }

    override fun onLaunchCameraClicked() {

    }

    override fun onOpenSourceMenuClicked() {
        baseView.openGithubPage()
    }
}
