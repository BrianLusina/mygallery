package com.mygallery.di.modules

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.mygallery.data.io.SchedulerProvider
import com.mygallery.data.io.SchedulerProviderImpl
import com.mygallery.di.qualifier.ActivityCtxQualifier
import com.mygallery.di.scopes.ActivityScope
import com.mygallery.ui.albums.AlbumsPresenter
import com.mygallery.ui.albums.AlbumsPresenterImpl
import com.mygallery.ui.albums.AlbumsRecyclerAdapter
import com.mygallery.ui.albums.AlbumsView
import com.mygallery.ui.photo.PhotoPagerPresenter
import com.mygallery.ui.photo.PhotoPagerPresenterImpl
import com.mygallery.ui.photo.PhotoPagerView
import com.mygallery.ui.photo.PhotoPagerAdapter
import com.mygallery.ui.singlealbum.SingleAlbumPresenter
import com.mygallery.ui.singlealbum.SingleAlbumPresenterImpl
import com.mygallery.ui.singlealbum.SingleAlbumView
import com.mygallery.ui.singlealbum.grid.GridPresenter
import com.mygallery.ui.singlealbum.grid.GridPresenterImpl
import com.mygallery.ui.singlealbum.grid.GridRecyclerAdapter
import com.mygallery.ui.singlealbum.grid.GridView
import com.mygallery.ui.splash.SplashPresenter
import com.mygallery.ui.splash.SplashPresenterImpl
import com.mygallery.ui.splash.SplashView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * @author lusinabrian on 20/09/17.
 * @Notes module for activities
 */
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    @ActivityCtxQualifier
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideSchedulers(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @ActivityScope
    fun provideAlbumsPresenter(albumsPresenter: AlbumsPresenterImpl<AlbumsView>): AlbumsPresenter<AlbumsView> {
        return albumsPresenter
    }

    @Provides
    fun provideAlbumsAdapter(): AlbumsRecyclerAdapter {
        return AlbumsRecyclerAdapter(ArrayList())
    }

    @Provides
    @ActivityScope
    fun provideSplashPresenter(splashPresenter: SplashPresenterImpl<SplashView>): SplashPresenter<SplashView> {
        return splashPresenter
    }

    @Provides
    @ActivityScope
    fun provideSingleAlbumPresenter(singleAlbumPresenter: SingleAlbumPresenterImpl<SingleAlbumView>)
            : SingleAlbumPresenter<SingleAlbumView> {
        return singleAlbumPresenter
    }

    @Provides
    @ActivityScope
    fun provideGridPresenter(gridPresenter: GridPresenterImpl<GridView>): GridPresenter<GridView> {
        return gridPresenter
    }

    @Provides
    fun provideGridRecyclerAdapter(): GridRecyclerAdapter {
        return GridRecyclerAdapter(arrayListOf())
    }

    @Provides
    @ActivityScope
    fun providePhotoPagerPresenter(photoPagerPresenter: PhotoPagerPresenterImpl<PhotoPagerView>): PhotoPagerPresenter<PhotoPagerView> {
        return photoPagerPresenter
    }

    @Provides
    fun providePhotoPagerAdapter(): PhotoPagerAdapter {
        return PhotoPagerAdapter(activity.supportFragmentManager)
    }
}
