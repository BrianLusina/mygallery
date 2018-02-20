package com.mygallery.data.files

import com.mygallery.data.models.AlbumModel

/**
 * @author lusinabrian on 06/11/17.
 * @Notes File helper interface to handle file actions
 */
interface FileHelper {

    /**
     * Get a list of image folder paths
     * @return [ArrayList] Album Model list
     * */
    /**
     * Saves data to shared preferences
     * this is an extension function that
     * @param key key to use to save data
     * @param value the value to use to save data
     * extension functions that allows the saving of data to a shared preference file
     * */
    fun getAllImagePaths() : ArrayList<AlbumModel>

    /**
     * Gets a list of video folders
     * @return [ArrayList] AlbumModel array list
     * */
    fun getListOfVideoFolders() : ArrayList<AlbumModel>

    /**
     * Gets all the shown images from the given folder name
     * @param folderName Folder name to retrieve images from
     * @param isVideo Whether we are dealing with videos or not
     * */
    fun getAllShownImagesPath(folderName: String, isVideo: Boolean) : ArrayList<String>
}
