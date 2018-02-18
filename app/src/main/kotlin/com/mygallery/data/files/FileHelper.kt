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