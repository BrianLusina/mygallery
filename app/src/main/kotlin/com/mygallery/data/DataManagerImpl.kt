package com.mygallery.data

import android.graphics.Bitmap
import android.net.Uri
import com.mygallery.data.files.FileHelper
import com.mygallery.data.prefs.SharedPrefsHelper
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Implementation for the datamanager
 */
@Singleton
class DataManagerImpl
@Inject
constructor(val prefsHelper: SharedPrefsHelper, val fileHelper: FileHelper) : DataManager {

    override fun saveImageFilePath(imageFileKey: String, imageFilePath: String) {
        prefsHelper.saveImageFilePath(imageFileKey, imageFilePath)
    }

    override fun getImageFilePath(imageFileKey: String): String {
        return prefsHelper.getImageFilePath(imageFileKey)
    }

    override fun deleteImageFile(photoPath: String): Boolean {
        return fileHelper.deleteImageFile(photoPath)
    }

    override fun saveImageFile(mResultsBitmap: Bitmap?): String? {
        return fileHelper.saveImageFile(mResultsBitmap)
    }

    override fun createTempImageFile(): Pair<File?, Uri> {
        return fileHelper.createTempImageFile()
    }
}