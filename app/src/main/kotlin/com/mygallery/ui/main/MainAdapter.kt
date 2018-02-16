package com.mygallery.ui.main

import android.view.ViewGroup
import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseRecyclerAdapter
import com.mygallery.ui.base.BaseViewHolder
import javax.inject.Inject

/**
 * @author lusinabrian on 16/02/18.
 * @Notes Main Adapter
 */
class MainAdapter
@Inject
constructor(private var albumList : ArrayList<AlbumModel>) : BaseRecyclerAdapter<AlbumModel>(albumList){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<AlbumModel> {

    }
}