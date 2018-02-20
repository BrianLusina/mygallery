package com.mygallery.data.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author lusinabrian on 13/02/18.
 * @Notes Album Model is the model representation for the albums in the device
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class AlbumModel(
        var folderNames : String,
        var imagePath: String,
        var imageCount : Int,
        var isVideo: Boolean
) : Parcelable
