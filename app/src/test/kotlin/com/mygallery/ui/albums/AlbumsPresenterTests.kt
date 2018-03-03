import android.content.Context
import com.mygallery.data.io.TestSchedulerProvider
import com.mygallery.data.DataManager
import com.mygallery.ui.albums.AlbumsPresenter
import com.mygallery.ui.albums.AlbumsPresenterImpl
import com.mygallery.ui.albums.AlbumsView
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
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 13/02/18
 * @Notes Tests for AlbumsPresenterTests
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class AlbumsPresenterTests {

    @Mock
    lateinit var mockAlbumsView: AlbumsView

    @Mock
    lateinit var mockDataManager: DataManager

    @Mock
    lateinit var mockedContext: Context

    lateinit var albumsPresenter: AlbumsPresenter<AlbumsView>
    lateinit var mTestScheduler: TestScheduler

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
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        albumsPresenter = AlbumsPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        albumsPresenter.onAttach(mockAlbumsView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        albumsPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnResumeSetsUpViews(){
        albumsPresenter.onResume()

        verify(mockAlbumsView, times(1)).setUpToolbar()
        verify(mockAlbumsView, times(1)).setupNavigationView()
        verify(mockAlbumsView, times(1)).setupDrawerLayout()
        verify(mockAlbumsView, times(1)).setupRecyclerAdapter()
    }

    @Test
    fun testOnRetrieveBundleSetsUpRetrievalOfIntentBundle(){
        albumsPresenter.onRetrieveBundle()

        verify(mockAlbumsView, times(1)).retrieveBundleFromIntent()
    }

    @Test
    fun testOnDrawerOpenedSetsBackButton(){
        albumsPresenter.onDrawerOpened()

        verify(mockAlbumsView, times(1)).setupBackIconOnDrawer()
    }

    @Test
    fun testOnDrawerClosedSetsMenuIcon(){
        albumsPresenter.onDrawerClosed()

        verify(mockAlbumsView, times(1)).setupMenuIconOnDrawer()
    }

    @Test
    fun testOnOpenSourceMenuItemClicked(){
        albumsPresenter.onOpenSourceMenuClicked()

        verify(mockAlbumsView, times(1)).openGithubPage()
    }
}