package com.mygallery.ui.singlealbum.grid

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.support.v4.app.SharedElementCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mygallery.R
import com.mygallery.ui.base.BaseFragment
import com.mygallery.ui.photo.PhotoPagerFragment
import com.mygallery.utils.BUNDLE_KEY_CURRENT_POSITION
import com.mygallery.utils.BUNDLE_KEY_IMAGE_ARRAY
import com.mygallery.utils.INTENT_KEY_ALBUM_FOLDER_NAME
import com.mygallery.utils.INTENT_KEY_ALBUM_IS_VIDEO
import kotlinx.android.synthetic.main.fragment_grid.view.*
import org.jetbrains.anko.find
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
 * @author lusinabrian on 21/02/18.
 * @Notes Grid Fragment to display a Grid of images for an album
 */
class GridFragment : BaseFragment(), GridView, GridRecyclerAdapter.Callback {

    @Inject
    lateinit var gridPresenter: GridPresenter<GridView>

    @Inject
    lateinit var gridRecyclerAdapter: GridRecyclerAdapter

    lateinit var gridView : View
    // lateinit var recyclerView : RecyclerView

    private var folderName = ""
    private var isVideo = false
    private var currentPosition: Int = 0
    private var photoArrayList = arrayListOf<String>()
    private val enterTransitionStarted = AtomicBoolean()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        activityComponent.injectGridFragment(this)

        gridPresenter.onAttach(this)

        gridRecyclerAdapter.callback = this

        gridView = inflater.inflate(R.layout.fragment_grid, container, false)

        // set adapter
        with(gridView){
            recycler_view_single_album.adapter = gridRecyclerAdapter
        }

        //fixme: Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'int java.util.ArrayList.size()' on a null object reference

        gridPresenter.onCreateView()

        return gridView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridPresenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        gridPresenter.onResume()
    }

    override fun retrieveBundleFromArguments() {
        if (arguments != null){
            folderName = arguments!!.getString(INTENT_KEY_ALBUM_FOLDER_NAME)
            isVideo = arguments!!.getBoolean(INTENT_KEY_ALBUM_IS_VIDEO)
            currentPosition = arguments!!.getInt(BUNDLE_KEY_CURRENT_POSITION)
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context).inflateTransition(
                R.transition.transition_grid_exit)
        setExitSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?,
                                             sharedElements: MutableMap<String, View>?) {
                val selectedViewHolder = gridView.recycler_view_single_album
                        .findViewHolderForAdapterPosition(currentPosition)

                if(selectedViewHolder?.itemView == null){
                    return
                }

                // map the first shared element to the child view
                sharedElements?.put(names!![0],
                        selectedViewHolder.itemView.findViewById(R.id.image_view_thumbnail))
            }
        })

        postponeEnterTransition()
    }

    override fun scrollToPosition() {
        gridView.recycler_view_single_album.addOnLayoutChangeListener(object : View.OnLayoutChangeListener{
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int,
                                        oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {

                gridView.recycler_view_single_album.removeOnLayoutChangeListener(this)

                with(gridView){
                    val layoutManager = recycler_view_single_album.layoutManager
                    val viewAtPosition = layoutManager.findViewByPosition(currentPosition)

                    // Scroll to position if the view for the current position is null (not currently
                    // part of layout manager children), or it's not completely visible.
                    if(viewAtPosition == null || layoutManager.isViewPartiallyVisible(
                                    viewAtPosition, false, true)){
                        recycler_view_single_album.post { layoutManager.scrollToPosition(currentPosition) }
                    }
                }
            }
        })
    }

    override fun addItemsToAdapter(imageList: ArrayList<String>) {
        photoArrayList = imageList
        gridRecyclerAdapter.addItemsUsingDiff(imageList)
    }

    @SuppressLint("NewApi")
    override fun onItemClick(view: View, adapterPosition: Int) {
        // update the position
        currentPosition = adapterPosition

        // Exclude the clicked card from the exit transition (e.g. the card will disappear
        // immediately instead of fading out with the rest to prevent an
        // overlapping animation of fade and move).
        (exitTransition as TransitionSet).excludeTarget(view, true)

        val transitioningView = view.find<ImageView>(R.id.image_view_thumbnail)

        val photoPagerFragment = PhotoPagerFragment()
        val bundle = Bundle()
        bundle.putInt(BUNDLE_KEY_CURRENT_POSITION, currentPosition)
        bundle.putStringArrayList(BUNDLE_KEY_IMAGE_ARRAY, photoArrayList)
        photoPagerFragment.arguments = bundle

        fragmentManager
                ?.beginTransaction()
                ?.setReorderingAllowed(true) // Optimize for shared element transition
                ?.addSharedElement(transitioningView, transitioningView.transitionName)
                ?.replace(R.id.fragment_container, photoPagerFragment, PhotoPagerFragment::class
                        .simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun onLoadComplete(imageView: ImageView, adapterPosition: Int) {
        // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
        if(currentPosition != adapterPosition){
            return
        }

        if(enterTransitionStarted.getAndSet(true)){
            return
        }

        startPostponedEnterTransition()
    }

    override fun setupRecyclerAdapter() {
        gridPresenter.onRecyclerViewSetup(folderName, isVideo)
    }

    override fun setupToolbar() {
        //setSupportActionBar(toolbar)
        //supportActionBar?.title = folderName
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}