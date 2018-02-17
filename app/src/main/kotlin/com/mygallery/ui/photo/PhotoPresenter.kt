package com.mygallery.ui.photo

import com.mygallery.ui.base.BasePresenter

interface PhotoPresenter<V : PhotoView> : BasePresenter<V> {

    fun onResume()

    fun onRetrieveBundle()

}