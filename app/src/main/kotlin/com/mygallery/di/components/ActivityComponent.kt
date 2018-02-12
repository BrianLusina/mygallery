package com.mygallery.di.components

import com.mygallery.ui.main.MainActivity
import com.mygallery.di.modules.ActivityModule
import com.mygallery.di.scopes.ActivityScope
import com.mygallery.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(modules = [(ActivityModule::class)], dependencies = [(AppComponent::class)])
interface ActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)

    fun injectSplashActivity(splashActivity : SplashActivity)

}