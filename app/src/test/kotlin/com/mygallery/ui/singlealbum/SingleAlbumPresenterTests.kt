import android.content.Context
import com.mygallery.data.DataManager
import com.mygallery.data.io.TestSchedulerProvider
import com.mygallery.ui.singlealbum.SingleAlbumPresenter
import com.mygallery.ui.singlealbum.SingleAlbumPresenterImpl
import com.mygallery.ui.singlealbum.SingleAlbumView
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
 * @Notes Tests for SingleAlbumPresenterTests
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class SingleAlbumPresenterTests {

    @Mock lateinit var mockSingleAlbumView : SingleAlbumView
    @Mock lateinit var mockDataManager: DataManager
    @Mock lateinit var mockedContext: Context

    lateinit var singleAlbumPresenter : SingleAlbumPresenter<SingleAlbumView>
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

        singleAlbumPresenter = SingleAlbumPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        singleAlbumPresenter.onAttach(mockSingleAlbumView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        singleAlbumPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnResumeSetsUpViews(){
        singleAlbumPresenter.onResume()

        verify(mockSingleAlbumView, times(1)).setupToolbar()
        verify(mockSingleAlbumView, times(1)).setupRecyclerAdapter()
    }

    @Test
    fun testOnRetrieveBundleReceivesIntent(){
        singleAlbumPresenter.onRetrieveBundle()

        verify(mockSingleAlbumView, times(1)).retrieveBundleFromIntent()
    }

    @Test
    fun testOnRecyclerViewSetup(){
        singleAlbumPresenter.onRecyclerViewSetup("Camera", false)

        // verify that the data manager is called
        verify(mockDataManager, times(1)).getAllShownImagesPath("Camera", false)

        // verify that the view is called to setup adapter items
        verify(mockSingleAlbumView, times(1)).addItemsToAdapter(arrayListOf())
    }
}