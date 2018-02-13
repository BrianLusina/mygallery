package com.mygallery.ui.splash

import android.os.Bundle
import com.mygallery.R
import com.mygallery.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter<SplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityComponent.injectSplashActivity(this)

        splashPresenter.onAttach(this)
    }
}
