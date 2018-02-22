import android.content.Context
import com.mygallery.data.DataManager
import com.mygallery.data.io.TestSchedulerProvider
import com.mygallery.ui.singlealbum.grid.GridPresenter
import com.mygallery.ui.singlealbum.grid.GridPresenterImpl
import com.mygallery.ui.singlealbum.grid.GridView
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
 * @Notes Tests for GridPresenterTests
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class GridPresenterTests {

    @Mock lateinit var mockGridView: GridView
    @Mock lateinit var mockDataManager: DataManager
    @Mock lateinit var mockedContext: Context

    lateinit var gridPresenter: GridPresenter<GridView>
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

        gridPresenter = GridPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        gridPresenter.onAttach(mockGridView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        gridPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnResumeSetsUpViews(){
        gridPresenter.onResume()

        verify(mockGridView, times(1)).setupToolbar()
        verify(mockGridView, times(1)).setupRecyclerAdapter()
    }

    @Test
    fun testOnRetrieveBundleReceivesIntent(){
        gridPresenter.onRetrieveBundle()

        verify(mockGridView, times(1)).retrieveBundleFromIntent()
    }

    @Test
    fun testOnRecyclerViewSetup(){
        gridPresenter.onRecyclerViewSetup("Camera", false)

        // verify that the data manager is called
        verify(mockDataManager, times(1)).getAllShownImagesPath("Camera", false)

        // verify that the view is called to setup adapter items
        verify(mockGridView, times(1)).addItemsToAdapter(arrayListOf())
    }
}