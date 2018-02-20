package com.mygallery.ui.albums

import com.mygallery.ui.base.BaseView

interface AlbumsView : BaseView {

    fun retrieveBundleFromIntent()

    /**
     * Sets up toolbar
     * */
    fun setUpToolbar()

    /**
     * Sets up navigation view
     * */
    fun setupNavigationView()

    /**
     * Sets up Drawer Layout
     * */
    fun setupDrawerLayout()

    /**
     * Setup Back button on Drawer
     * */
    fun setupBackIconOnDrawer()

    /**
     * Setup Menu icon on Drawer
     * */
    fun setupMenuIconOnDrawer()

    /**
     * Sets up recycler adapter
     * */
    fun setupRecyclerAdapter()

    /**
     * Open Github page
     * */
    fun openGithubPage()
}
