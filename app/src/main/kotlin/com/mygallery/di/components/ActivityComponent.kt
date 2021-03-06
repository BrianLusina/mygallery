package com.mygallery.di.components

import com.mygallery.di.modules.ActivityModule
import com.mygallery.di.scopes.ActivityScope
import com.mygallery.ui.albums.AlbumsActivity
import com.mygallery.ui.photo.PhotoPagerFragment
import com.mygallery.ui.singlealbum.grid.GridFragment
import com.mygallery.ui.singlealbum.SingleAlbumActivity
import com.mygallery.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(modules = [(ActivityModule::class)], dependencies = [(AppComponent::class)])
interface ActivityComponent {

    fun injectAlbumsActivity(albumsActivity: AlbumsActivity)

    fun injectSplashActivity(splashActivity: SplashActivity)

    fun injectSingleAlbumActivity(singleAlbumActivity: SingleAlbumActivity)

    fun injectGridFragment(gridFragment: GridFragment)

    fun injectPhotoPagerFragment(photoPagerFragment: PhotoPagerFragment)
}
