package com.mygallery.ui.photo

import com.mygallery.ui.base.BaseView

interface PhotoPagerView : BaseView {

    fun retrieveBundleFromArguments()

    fun prepareSharedElementTransition()

}
