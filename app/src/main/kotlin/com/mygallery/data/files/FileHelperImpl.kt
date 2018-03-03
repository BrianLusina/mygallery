package com.mygallery.data.files

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.mygallery.data.models.AlbumModel
import com.mygallery.di.qualifier.AppCtxQualifier
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 06/11/17.
 * @Notes Implementation for the file helper
 */
@Singleton
class FileHelperImpl @Inject constructor(@AppCtxQualifier val context: Context) : FileHelper {

    override fun getAllImagePaths(): ArrayList<AlbumModel> {
        val uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val listOfAllImages = ArrayList<String>()
        val albumsList = ArrayList<AlbumModel>()
        var cursorBucket: Cursor? = null
        val bucketGroupBy = "1) GROUP BY 1,(2"
        val bucketOrderBy = "MAX(datetaken) DESC"

        val projection = arrayOf(MediaStore.Images.ImageColumns.BUCKET_ID,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.DATA)
        val cursor = context.contentResolver.query(uri, projection, bucketGroupBy, null, bucketOrderBy)

        if (cursor != null) {
            val columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            val columnIndexFolderName = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val absolutePathOfImage = cursor.getString(columnIndexData)
                val selectionArgs = arrayOf("%" + cursor.getString(columnIndexFolderName) + "%")
                val selection = MediaStore.Images.Media.DATA + " like ? "
                val projectionOnlyBucket = arrayOf(MediaStore.MediaColumns.DATA,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                cursorBucket = context.contentResolver.query(uri, projectionOnlyBucket, selection, selectionArgs,
                        null)

                if (absolutePathOfImage != "" && absolutePathOfImage != null) {
                    listOfAllImages.add(absolutePathOfImage)
                    albumsList.add(AlbumModel(cursor.getString(columnIndexFolderName), absolutePathOfImage,
                            cursorBucket.count, false))
                }
            }
        }

        if (cursorBucket != null) {
            cursorBucket.close()
        }

        cursor.close()

        return albumsList
    }

    override fun getListOfVideoFolders(): ArrayList<AlbumModel> {
        var cursorBucket: Cursor? = null
        val bucketGroupBy = "1) GROUP BY 1,(2"
        val bucketOrderBy = "MAX(datetaken) DESC"
        val columnIndexAlbumName: Int
        val columnIndexAlbumVideo: Int
        val albumsList = ArrayList<AlbumModel>()

        val uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection1 = arrayOf(MediaStore.Video.VideoColumns.BUCKET_ID,
                MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Video.VideoColumns.DATE_TAKEN,
                MediaStore.Video.VideoColumns.DATA)

        val cursor = context.contentResolver.query(uri, projection1, bucketGroupBy, null, bucketOrderBy)

        if (cursor != null) {
            columnIndexAlbumName = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            columnIndexAlbumVideo = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            while (cursor.moveToNext()) {
                val selectionArgs = arrayOf("%" + cursor.getString(columnIndexAlbumName) + "%")

                val selection = MediaStore.Video.Media.DATA + " like ? "
                val projectionOnlyBucket = arrayOf(MediaStore.MediaColumns.DATA,
                        MediaStore.Video.Media.BUCKET_DISPLAY_NAME)

                cursorBucket = context.contentResolver.query(uri, projectionOnlyBucket, selection,
                        selectionArgs, null)

                albumsList.add(AlbumModel(cursor.getString(columnIndexAlbumName),
                        cursor.getString(columnIndexAlbumVideo), cursorBucket.count, true))
            }
        }

        if (cursorBucket != null) {
            cursorBucket.close()
        }

        cursor.close()

        return albumsList
    }

    override fun getAllShownImagesPath(folderName: String, isVideo: Boolean): ArrayList<String> {
        val listOfAllImages = ArrayList<String>()
        var absolutePathOfImage: String

        val selectionArgs = arrayOf("%$folderName%")

        val uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Images.Media.DATA + " like ? "

        val projectionOnlyBucket = arrayOf(MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        val cursorBucket = context.contentResolver.query(uri, projectionOnlyBucket, selection,
                selectionArgs, null)

        val columnIndexData = cursorBucket.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        while (cursorBucket.moveToNext()) {
            absolutePathOfImage = cursorBucket.getString(columnIndexData)
            if (absolutePathOfImage != "" && absolutePathOfImage != null)
                listOfAllImages.add(absolutePathOfImage)
        }

        cursorBucket.close()

        return listOfAllImages
    }
}
