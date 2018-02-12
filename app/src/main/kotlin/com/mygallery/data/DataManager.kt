package com.mygallery.data

import com.mygallery.data.files.FileHelper
import com.mygallery.data.prefs.SharedPrefsHelper

/**
 * @author lusinabrian on 23/09/17.
 * @Notes interface for model layer to delegate model functions
 */
interface DataManager : SharedPrefsHelper, FileHelper {
}