package com.mygallery.ui.singlealbum

import com.mygallery.ui.base.BasePresenter

interface SingleAlbumPresenter<V : SingleAlbumView> : BasePresenter<V> {

    fun onResume()

    fun onCreateView()
}
