package com.mygallery.ui.main

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * @author lusinabrian on 16/02/18.
 * @Notes Main View holder
 */
class MainViewHolder(itemView: View, private val albumModelList: ArrayList<AlbumModel>) : BaseViewHolder<AlbumModel>(itemView){

    @SuppressLint("SetTextI18n")
    override fun onBind(position: Int) {
        super.onBind(position)
        val albumItem = albumModelList[position]

        with(itemView){
            Glide.with(context)
                    .load(albumItem.imagePath)
                    .into(image_view_thumbnail)
            text_view_photo_count.text = "${albumItem.imageCount} ${ if(albumItem.isVideo) "videos" else "photos"}"
            text_view_title.text = albumItem.folderNames

            setOnClickListener({})
        }
    }

}