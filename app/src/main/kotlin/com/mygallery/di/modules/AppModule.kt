package com.mygallery.di.modules

import android.app.Application
import android.content.Context
import com.mygallery.data.DataManager
import com.mygallery.data.DataManagerImpl
import com.mygallery.data.files.FileHelper
import com.mygallery.data.files.FileHelperImpl
import com.mygallery.data.prefs.SharedPrefsHelper
import com.mygallery.data.prefs.SharedPrefsHelperImpl
import com.mygallery.di.qualifier.AppCtxQualifier
import com.mygallery.di.qualifier.PreferenceInfo
import com.mygallery.utils.SHARED_PREFS_NAME
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app module
 */
@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @AppCtxQualifier
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @PreferenceInfo
    fun provideSharedPrefsName(): String {
        return SHARED_PREFS_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(dataManager: DataManagerImpl): DataManager {
        return dataManager
    }

    @Provides
    @Singleton
    fun provideFileHelper(fileHelper: FileHelperImpl): FileHelper {
        return fileHelper
    }

    @Provides
    @Singleton
    fun providePrefsHelper(sharedPrefsHelper: SharedPrefsHelperImpl): SharedPrefsHelper {
        return sharedPrefsHelper
    }

    @Provides
    @Named("NetworkSubject")
    fun provideNetworkSubject(): PublishSubject<Boolean> {
        return PublishSubject.create()
    }
}
