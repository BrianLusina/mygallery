package com.mygallery.ui.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.mygallery.R
import com.mygallery.data.models.AlbumModel
import com.mygallery.ui.base.BaseActivity
import com.mygallery.ui.main.MainActivity
import com.mygallery.utils.INTENT_KEY_ALBUM_URL_DATA
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter<SplashView>

    private val requestStoragePermissionReqCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityComponent.injectSplashActivity(this)

        splashPresenter.onAttach(this)
    }

    override fun onResume() {
        super.onResume()
        splashPresenter.onResume()
    }

    override fun checkForPermissionToExternalStorage() {
        if(hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
            // proceed to load images
            splashPresenter.onLoadImages()
        } else {
            // request for permission
            splashPresenter.onRequestForStoragePermission()
        }
    }

    override fun requestForStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            requestPermissionsSafely(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), requestStoragePermissionReqCode)
        }
    }

    override fun displayPermissionRationale() {
        MaterialDialog.Builder(this)
                .title(R.string.title_dialog_storage_permission)
                .content(R.string.content_dialog_storage_permission)
                .positiveText(R.string.action_dialog_positive)
                .onPositive{dialog, _ ->
                    splashPresenter.onRequestForStoragePermission()
                    dialog.dismiss()
                }
                .negativeText(R.string.action_dialog_negative)
                .onNegative{ dialog, _ ->
                    if(dialog.isShowing){
                        dialog.dismiss()
                        finish()
                    }
                }
                .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            requestStoragePermissionReqCode -> {
                if(grantResults.isNotEmpty()){
                    if(grantResults.first() == PackageManager.PERMISSION_GRANTED){
                        splashPresenter.onLoadImages()
                    } else {
                      splashPresenter.onStoragePermissionDenied()
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openMainActivity(albumList: ArrayList<AlbumModel>) {
        startActivity<MainActivity>(INTENT_KEY_ALBUM_URL_DATA to albumList)
    }
}
