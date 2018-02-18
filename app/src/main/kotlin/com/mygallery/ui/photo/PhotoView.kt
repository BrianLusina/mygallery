package com.mygallery.ui.photo

import com.mygallery.ui.base.BaseView

interface PhotoView : BaseView {

    fun retrieveBundleFromIntent()

    fun setupToolbar()

    fun setupFullScreenImage()
}