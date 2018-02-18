package com.mygallery.ui.singlealbum

import com.mygallery.ui.base.BasePresenter

interface SingleAlbumPresenter<V : SingleAlbumView> : BasePresenter<V> {

    /**
     * Retrieve Bundle
     * */
    fun onRetrieveBundle()

    fun onResume()

    /**
     * Once the recycler view has been setup, we load the photos
     * @param folderName Folder name to load photos from
     * @param isVideo Whether the album has videos
     * */
    fun onRecyclerViewSetup(folderName : String, isVideo : Boolean)

}