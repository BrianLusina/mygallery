package com.mygallery.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.mygallery.di.qualifier.AppCtxQualifier
import com.mygallery.di.qualifier.PreferenceInfo
import javax.inject.Inject

/**
 * @author lusinabrian on 07/11/17.
 * @Notes Prefs helper implementation
 */
class SharedPrefsHelperImpl
@Inject
constructor(@AppCtxQualifier val context: Context,
            @PreferenceInfo val prefFileName: String): SharedPrefsHelper {

    private fun <T> SharedPreferences.saveData(key: String, value: T) {
        when (value) {
            is String -> this.edit().putString(key, value.toString()).apply()
            is Boolean -> this.edit().putBoolean(key, value).apply()
            is Int -> this.edit().putInt(key, value.toInt()).apply()
            is Float -> this.edit().putFloat(key, value.toFloat()).apply()
            is Long -> this.edit().putLong(key, value.toLong()).apply()
        }
    }

    private val mSharedPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun saveImageFilePath(imageFileKey: String, imageFilePath: String) {
        mSharedPrefs.saveData(imageFileKey, imageFilePath)
    }

    override fun getImageFilePath(imageFileKey: String): String {
        return mSharedPrefs.getString(imageFileKey, "")
    }
}
