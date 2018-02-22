package com.mygallery.ui.singlealbum.fragment

import android.os.Build
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mygallery.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_grid.view.*
import java.lang.Exception

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
        val photoItemPath = albumList[position]

        with(itemView) {
            Glide.with(context)
                    .load(photoItemPath)
                    .listener(object : RequestListener<String, GlideDrawable>{
                        override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                            callBack.onLoadComplete(image_view_thumbnail, adapterPosition)
                            return false
                        }

                        override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                            callBack.onLoadComplete(image_view_thumbnail, adapterPosition)
                            return false
                        }
                    })
                    .thumbnail(1f)
                    .into(image_view_thumbnail)

            // Set the string value of the image resource as the unique transition name for the view.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image_view_thumbnail.transitionName = photoItemPath
            }

            setOnClickListener {
                callBack.onItemClick(this, adapterPosition)
            }
        }
    }
}
