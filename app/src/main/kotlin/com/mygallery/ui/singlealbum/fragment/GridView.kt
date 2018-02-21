package com.mygallery.ui.singlealbum.fragment

import com.mygallery.ui.base.BaseView

interface GridView : BaseView {

    /**
     * Sets up Toolbar
     * */
    fun setupToolbar()

    /**
     * Sets up Recycler Adapter
     * */
    fun setupRecyclerAdapter()

    /**
     * Receives Bundle from intent
     * */
    fun retrieveBundleFromArguments()

    /**
     * Adds Items to adapter
     * @param imageList Array List of images from the given folder path
     * */
    fun addItemsToAdapter(imageList: ArrayList<String>)

    /**
     * Prepares the shared element transition to the pager fragment
     * */
    fun prepareTransitions()

    /**
     * Scrolls the recycler view to show the last viewed item in the grid. This is important when
     * navigating back from the grid.
     */
    fun scrollToPosition()
}
