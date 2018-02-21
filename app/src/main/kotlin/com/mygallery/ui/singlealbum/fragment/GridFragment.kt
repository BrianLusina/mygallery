package com.mygallery.ui.singlealbum.fragment

import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.SharedElementCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mygallery.ui.base.BaseFragment
import com.mygallery.ui.photo.PhotoActivity
import com.mygallery.utils.BUNDLE_KEY_CURRENT_POSITION
import com.mygallery.utils.INTENT_KEY_PHOTO_ITEM_PATH
import com.mygallery.utils.INTENT_KEY_SINGLE_ALBUM_FOLDER_NAME
import com.mygallery.utils.INTENT_KEY_SINGLE_ALBUM_IS_VIDEO
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

    lateinit var recyclerView : RecyclerView

    private var folderName = ""
    private var isVideo = false
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gridPresenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        activityComponent.injectGridFragment(this)

        gridPresenter.onAttach(this)

        gridRecyclerAdapter.callback = this

        recyclerView = inflater.inflate(R.layout.fragment_grid, container, false)

        // set adapter
        recyclerView.adapter = gridRecyclerAdapter

        gridPresenter.onCreateView()

        return recyclerView
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
            folderName = arguments!!.getString(INTENT_KEY_SINGLE_ALBUM_FOLDER_NAME)
            isVideo = arguments!!.getBoolean(INTENT_KEY_SINGLE_ALBUM_IS_VIDEO)
            currentPosition = arguments!!.getInt(BUNDLE_KEY_CURRENT_POSITION)
        }
    }

    override fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition_grid_exit)
        setExitSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val selectedViewHolder = recyclerView.findViewHolderForAdapterPosition(currentPosition)

                if(selectedViewHolder?.itemView == null){
                    return
                }

                // map the first shared element to the child view
                sharedElements?.put(names!![0], selectedViewHolder.itemView.findViewById(R.id.image_view_thumbnail))
            }
        })

        postponeEnterTransition()
    }

    override fun scrollToPosition() {
        recyclerView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener{
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                recyclerView.removeOnLayoutChangeListener(this)
                val layoutManager = recyclerView.layoutManager
                val viewAtPosition = layoutManager.findViewByPosition(currentPosition)

                // Scroll to position if the view for the current position is null (not currently part of
                // layout manager children), or it's not completely visible.
                if(viewAtPosition == null || layoutManager.isViewPartiallyVisible(
                                viewAtPosition, false, true)){
                    recyclerView.post { layoutManager.scrollToPosition(currentPosition) }
                }
            }
        })
    }

    override fun setUp(view: View) {

    }

    override fun addItemsToAdapter(imageList: ArrayList<String>) {
        gridRecyclerAdapter.addItemsUsingDiff(imageList)
    }

    override fun onSinglePhotoClick(photoItemName: String) {
        startActivity<PhotoActivity>(INTENT_KEY_PHOTO_ITEM_PATH to photoItemName)
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