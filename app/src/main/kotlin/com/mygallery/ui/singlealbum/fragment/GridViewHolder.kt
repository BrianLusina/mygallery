package com.mygallery.ui.singlealbum.fragment

import android.view.View
import com.bumptech.glide.Glide
import com.mygallery.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_single_album.view.*

/**
 * @author lusinabrian on 17/02/18.
 * @Notes
 */
class GridViewHolder(itemView: View, val albumList: ArrayList<String>) : BaseViewHolder<String>(itemView) {
    lateinit var callBack: GridRecyclerAdapter.Callback

    constructor(itemView: View, albumList: ArrayList<String>, callBack: GridRecyclerAdapter.Callback)
            : this(itemView, albumList) {
        this.callBack = callBack
    }

    override fun onBind(position: Int) {
        super.onBind(position)
        val photoItem = albumList[position]

        with(itemView) {
            Glide.with(context)
                    .load(photoItem)
                    .thumbnail(1f)
                    .into(image_view_thumbnail)

            setOnClickListener {
                callBack.onSinglePhotoClick(photoItem)
            }
        }
    }
}
