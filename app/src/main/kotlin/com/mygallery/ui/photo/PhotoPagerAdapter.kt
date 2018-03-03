package com.mygallery.ui.photo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import javax.inject.Inject

/**
 * @author lusinabrian on 22/02/18.
 * @Notes Photo pager adapter to hold Fragment views of images
 */
class PhotoPagerAdapter
@Inject
constructor(childFragmentManager: FragmentManager) : FragmentStatePagerAdapter(childFragmentManager){
    var photoArrayList = arrayListOf<String>()

    override fun getItem(position: Int): Fragment {
        return PhotoFragment.newInstance(photoArrayList[position])
    }

    override fun getCount() = photoArrayList.size
}