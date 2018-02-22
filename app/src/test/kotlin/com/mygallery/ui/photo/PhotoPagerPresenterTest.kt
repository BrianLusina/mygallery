package com.mygallery.ui.photo
import com.mygallery.data.DataManager
import com.mygallery.data.io.TestSchedulerProvider
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 17/02/18
 * @Notes Tests for PhotoPagerPresenterTest
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class PhotoPagerPresenterTest {
    @Mock
    lateinit var mockPhotoPagerView: PhotoPagerView
    @Mock
    lateinit var mockDataManager: DataManager

    lateinit var photoPagerPagerPresenter: PhotoPagerPresenter<PhotoPagerView>
    val testScheduler = TestScheduler()

    companion object {
        @BeforeClass
        @JvmStatic
        @Throws(Exception::class)
        fun onlyOnce() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({
                Schedulers.trampoline()
            })
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        photoPagerPagerPresenter = PhotoPagerPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        photoPagerPagerPresenter.onAttach(mockPhotoPagerView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

        photoPagerPagerPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnResumeInitializesView() {
        photoPagerPagerPresenter.onResume()

        verify(mockPhotoPagerView, times(1)).setupToolbar()
        verify(mockPhotoPagerView, times(1)).setupFullScreenImage()
    }

    @Test
    fun testOnRetrieveBundleReceivesIntent(){
        photoPagerPagerPresenter.onRetrieveBundle()

        verify(mockPhotoPagerView, times(1)).retrieveBundleFromArguments()
    }
}