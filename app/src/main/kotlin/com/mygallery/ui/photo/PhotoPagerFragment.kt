package com.mygallery.ui.photo

import android.os.Bundle
import android.os.Handler
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.bumptech.glide.Glide
import com.mygallery.R
import com.mygallery.ui.base.BaseFragment
import com.mygallery.utils.BUNDLE_KEY_CURRENT_POSITION
import com.mygallery.utils.BUNDLE_KEY_IMAGE_ARRAY
import javax.inject.Inject

/**
 * @author lusinabrian on 17/02/18.
 * @Notes This displays a single Photo when clicked
 */
class PhotoPagerFragment : BaseFragment(), PhotoPagerView {

    @Inject
    lateinit var photoPagerPresenter: PhotoPagerPresenter<PhotoPagerView>

    @Inject
    lateinit var photoPagerAdapter : PhotoPagerAdapter

    lateinit var viewPager : ViewPager

    var currentPosition : Int = 0
    // set a dummy photo count, this will be updated when we get the Bundle arguments
    var photoCount : Int = 20
    var photoArrayList = arrayListOf<String>()

    companion object {
        const val KEY_CURRENT_POSITION = "KEY_CURRENT_POSITION"
        const val KEY_PHOTO_ARR_LIST = "KEY_PHOTO_ARR_LIST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION)
            photoArrayList = savedInstanceState.getStringArrayList(KEY_PHOTO_ARR_LIST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // inject dependencies
        activityComponent.injectPhotoPagerFragment(this)

        // attach your presenter
        photoPagerPresenter.onAttach(this)

        viewPager = inflater.inflate(R.layout.fragment_photo_pager, container, false) as ViewPager

        viewPager.adapter = photoPagerAdapter

        photoPagerAdapter.photoCount = photoCount
        photoPagerAdapter.photoArrayList = photoArrayList

        viewPager.currentItem = currentPosition
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                currentPosition = position
            }
        });

        photoPagerPresenter.onPrepareSharedElementTransition()

        if(savedInstanceState == null){
            postponeEnterTransition()
        }

        return viewPager
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CURRENT_POSITION, currentPosition)
        outState.putStringArrayList(KEY_PHOTO_ARR_LIST, photoArrayList)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION)
            photoArrayList = savedInstanceState.getStringArrayList(KEY_PHOTO_ARR_LIST)
        }
    }

    override fun retrieveBundleFromArguments() {
        if(arguments != null){
            currentPosition = arguments!!.getInt(BUNDLE_KEY_CURRENT_POSITION)
            photoArrayList = arguments!!.getStringArrayList(BUNDLE_KEY_IMAGE_ARRAY)
            photoCount = photoArrayList.size
        }
    }

    override fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.transition_photo_shared_element)
        sharedElementEnterTransition = transition

        // similar mapping at the GridFragment with setExit
        setEnterSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?,
                                             sharedElements: MutableMap<String, View>?) {
                super.onMapSharedElements(names, sharedElements)
                // Locate the image view at the primary fragment (the PhotoFragment that is currently
                // visible). To locate the fragment, call instantiateItem with the selection position.
                // At this stage, the method will simply return the fragment at the position and will
                // not create a new one.
                val currentFragment = viewPager.adapter?.instantiateItem(viewPager, currentPosition)
                        as Fragment
                val view = currentFragment.view ?: return

                // Map the first shared element name to the child ImageView.
                sharedElements?.put(names!![0], view.findViewById(R.id.image_view_full_screen))
            }
        })
    }
}
