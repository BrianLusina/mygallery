package com.mygallery.ui.photo

import com.mygallery.ui.base.BasePresenter

interface PhotoPagerPresenter<V : PhotoPagerView> : BasePresenter<V> {

    fun onCreate()

    /**
     * Prepares for the shared element transition
     * */
    fun onPrepareSharedElementTransition()

    fun onRetrieveBundle()
}
