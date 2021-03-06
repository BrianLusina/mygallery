package com.mygallery.ui.singlealbum.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mygallery.R
import com.mygallery.ui.base.BaseRecyclerAdapter
import com.mygallery.ui.base.BaseViewHolder
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes Recycler Adapter for a single album
 */
class GridRecyclerAdapter
@Inject
constructor(private val albumList: ArrayList<String>) : BaseRecyclerAdapter<String>(albumList) {

    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<String> {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_grid, parent, false)
        return GridViewHolder(v, albumList, callback)
    }

    interface Callback {

        fun onLoadComplete(imageView : ImageView, adapterPosition: Int)

        fun onItemClick(view: View, adapterPosition: Int)
    }
}
