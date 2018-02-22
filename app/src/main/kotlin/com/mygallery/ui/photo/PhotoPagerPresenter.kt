package com.mygallery.ui.photo

import com.mygallery.ui.base.BasePresenter

interface PhotoPagerPresenter<V : PhotoPagerView> : BasePresenter<V> {

    /**
     * Prepares for the shared element transition
     * */
    fun onPrepareSharedElementTransition()

    fun onRetrieveBundle()
}
