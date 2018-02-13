package com.mygallery.ui.main

import android.os.Bundle
import com.mygallery.R
import com.mygallery.ui.base.BaseActivity
import com.mygallery.ui.splash.SplashView
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter<SplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.injectMainActivity(this)

        mainPresenter.onAttach(this)
    }


}
