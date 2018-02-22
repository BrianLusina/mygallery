package com.mygallery.ui.photo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mygallery.R
import com.mygallery.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_photo.view.*
import java.lang.Exception

/**
 * @author lusinabrian on 22/02/18.
 * @Notes Photo Fragment is a single photo displayed in full screen
 */
class PhotoFragment : BaseFragment(){

    companion object {
        const val BUNDLE_KEY_IMAGE_PATH = "BUNDLE_KEY_IMAGE_PATH"

        fun newInstance(photoPath : String) : PhotoFragment{
            val fragment = PhotoFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_KEY_IMAGE_PATH, photoPath)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)

        val bundle = arguments
        val photoPath = bundle?.getString(BUNDLE_KEY_IMAGE_PATH)

        with(view){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image_view_full_screen.transitionName = photoPath
            }
        }

        Glide.with(this)
                .load(photoPath)
                .listener(object: RequestListener<String, GlideDrawable>{
                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?,
                                             isFirstResource: Boolean): Boolean {
                        // The postponeEnterTransition is called on the parent PhotoPagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going in case of a failure.
                        parentFragment?.startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?,
                                                 isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        // The postponeEnterTransition is called on the parent PhotoPagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going in case of a failure.
                        parentFragment?.startPostponedEnterTransition()
                        return false
                    }
                })
                .into(view.image_view_full_screen)

        return view
    }
}