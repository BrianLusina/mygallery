package com.mygallery.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.mygallery.di.components.AppComponent
import com.mygallery.di.components.DaggerAppComponent
import com.mygallery.di.modules.AppModule
import io.fabric.sdk.android.Fabric

class MyGalleryApp : Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.injectApp(this)

        Fabric.with(this, Crashlytics())
        Fabric.with(this, Answers())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
