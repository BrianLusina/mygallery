package com.mygallery.ui.singlealbum
import com.mygallery.data.DataManager
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.ui.base.BasePresenterImpl
import com.mygallery.ui.singlealbum.SingleAlbumPresenter
import com.mygallery.ui.singlealbum.SingleAlbumView
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
) : BasePresenterImpl<V>(dataManager, schedulerProvider, compositeDisposable), SingleAlbumPresenter<V>{

}
