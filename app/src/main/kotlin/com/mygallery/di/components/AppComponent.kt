package com.mygallery.di.components

import android.app.Application
import android.content.Context
import com.mygallery.app.MyGalleryApp
import com.mygallery.data.DataManager
import com.mygallery.di.modules.AppModule
import com.mygallery.di.qualifier.AppCtxQualifier
import dagger.Component
import io.reactivex.subjects.PublishSubject
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app component
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun injectApp(myGalleryApp: MyGalleryApp)

    @AppCtxQualifier
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager

    /**
     * Network Subject. This is posted based on network events
     * */
    @Named("NetworkSubject")
    fun networkSubject(): PublishSubject<Boolean>
}
