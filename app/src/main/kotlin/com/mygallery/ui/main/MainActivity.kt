package com.mygallery.ui.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.AbsListView
import com.bumptech.glide.Glide
import com.mygallery.R
import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseActivity
import com.mygallery.utils.INTENT_KEY_ALBUM_URL_DATA
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.navigation_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, View.OnClickListener {

    @Inject
    lateinit var mainPresenter: MainPresenter<MainView>

    @Inject
    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    lateinit var albumArrayList: ArrayList<AlbumModel>

    companion object {
        const val KEY_ALBUM_BUNDLE = "KEY_ALBUM_BUNDLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.injectMainActivity(this)

        mainPresenter.onAttach(this)

        if(savedInstanceState != null){
            albumArrayList = savedInstanceState.get(KEY_ALBUM_BUNDLE) as ArrayList<AlbumModel>
        } else {
            mainPresenter.onRetrieveBundle()
        }
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelableArrayList(KEY_ALBUM_BUNDLE, albumArrayList)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null){
            albumArrayList = savedInstanceState.get(KEY_ALBUM_BUNDLE) as ArrayList<AlbumModel>
        }
    }

    override fun onClick(v: View?) {
        when(v){
            fab_camera -> {
                mainPresenter.onLaunchCameraClicked()
            }
        }
    }

    override fun setUpToolbar() {
        setSupportActionBar(toolbar)

        // Enable the Up button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_action_menu))
        supportActionBar!!.title = getString(R.string.title_toolbar)
    }

    override fun setupNavigationView() {
        navigation_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawer(Gravity.START)
            when(it.itemId){
                R.id.nav_all_folders -> {

                }

                R.id.nav_hidden_folders -> {

                }
            }
            false
        }
    }

    override fun setupDrawerLayout() {
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            override fun onDrawerClosed(drawerView: View) {
                mainPresenter.onDrawerClosed()
            }

            override fun onDrawerOpened(drawerView: View) {
                mainPresenter.onDrawerOpened()
            }
        })
    }

    override fun setupBackIconOnDrawer() {
        supportActionBar!!.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_action_back))
    }

    override fun setupMenuIconOnDrawer() {
        supportActionBar!!.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_action_menu))
    }

    override fun retrieveBundleFromIntent() {
        if(intent.extras != null || intent.getBundleExtra(INTENT_KEY_ALBUM_URL_DATA) != null){
            albumArrayList = intent.extras.get(INTENT_KEY_ALBUM_URL_DATA) as ArrayList<AlbumModel>
        }
    }

    override fun setupRecyclerAdapter() {
        val glide = Glide.with(this)

        recycler_view_albums.layoutManager = GridLayoutManager(this, 2)
        recycler_view_albums.setHasFixedSize(true)
        recycler_view_albums.adapter = mainRecyclerAdapter

        // add items to adapter
        mainRecyclerAdapter.addItemsUsingDiff(albumArrayList)

        recycler_view_albums.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        glide.resumeRequests()
                    }
                    AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL,
                    AbsListView.OnScrollListener.SCROLL_STATE_FLING -> {
                        glide.pauseRequests()
                    }
                }
            }
        })
    }
}
