package com.mygallery.ui.singlealbum.grid

import com.mygallery.ui.base.BasePresenter

interface GridPresenter<V : GridView> : BasePresenter<V> {

    fun onCreateView()

    fun onResume()

    fun onViewCreated()

    /**
     * Once the recycler view has been setup, we load the photos
     * @param folderName Folder name to load photos from
     * @param isVideo Whether the album has videos
     * */
    fun onRecyclerViewSetup(folderName: String, isVideo: Boolean)
}
