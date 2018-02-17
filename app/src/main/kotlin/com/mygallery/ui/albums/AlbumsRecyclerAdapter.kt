package com.mygallery.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mygallery.R
import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseRecyclerAdapter
import com.mygallery.ui.base.BaseViewHolder
import javax.inject.Inject

/**
 * @author lusinabrian on 16/02/18.
 * @Notes Main Adapter
 */
class AlbumsRecyclerAdapter
@Inject
constructor(private var albumList : ArrayList<AlbumModel>) : BaseRecyclerAdapter<AlbumModel>(albumList){

    lateinit var callback: Callback

    override fun onViewRecycled(holder: BaseViewHolder<AlbumModel>?) {
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<AlbumModel>?) {
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<AlbumModel> {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(v, albumList, callback)
    }

    interface Callback{

        fun onAlbumFolderClicked(folderName : String, isVideo: Boolean)
    }
}