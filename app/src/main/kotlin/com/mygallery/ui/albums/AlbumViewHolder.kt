package com.mygallery.ui.albums

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
class AlbumViewHolder(itemView: View, private val albumModelList: ArrayList<AlbumModel>)
    : BaseViewHolder<AlbumModel>(itemView) {

    lateinit var callBack: AlbumsRecyclerAdapter.Callback

    constructor(itemView: View, albumModelList: ArrayList<AlbumModel>,
                callBack: AlbumsRecyclerAdapter.Callback) : this(itemView, albumModelList) {
        this.callBack = callBack
    }

    @SuppressLint("SetTextI18n")
    override fun onBind(position: Int) {
        super.onBind(position)
        val albumItem = albumModelList[position]

        with(itemView) {
            Glide.with(context)
                    .load(albumItem.imagePath)
                    .into(image_view_thumbnail)

            val videoText = "video${if (albumItem.imageCount > 1) "s" else ""}"
            val photosText = "photo${if (albumItem.imageCount > 1) "s" else ""}"

            text_view_photo_count.text = "${albumItem.imageCount} ${if (albumItem.isVideo)
                videoText
            else
                photosText
            }"

            text_view_title.text = albumItem.folderNames

            setOnClickListener {
                callBack.onAlbumFolderClicked(albumItem.folderNames, albumItem.isVideo)
            }
        }
    }
}
