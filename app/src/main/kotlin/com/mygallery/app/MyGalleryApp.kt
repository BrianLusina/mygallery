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
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter
import com.mygallery.BuildConfig


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

        AppCenter.start(this,
                BuildConfig.APP_CENTER_KEY,
                Analytics::class.java, Crashes::class.java)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
