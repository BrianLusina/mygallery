package com.mygallery.di.modules

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.data.io.SchedulerProviderImpl
import com.mygallery.di.qualifier.ActivityCtxQualifier
import com.mygallery.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * @author lusinabrian on 20/09/17.
 * @Notes module for activities
 */
@Module
class ActivityModule(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityCtxQualifier
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    fun provideSchedulers(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

//    @Provides
//    @ActivityScope
//    fun provideMainPresenter(photoCapturePresenter: PhotoCapturePresenterImpl<PhotoCaptureView>): PhotoCapturePresenter<PhotoCaptureView> {
//        return photoCapturePresenter
//    }
}