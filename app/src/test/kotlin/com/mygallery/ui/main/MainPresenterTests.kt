import android.content.Context
import com.mygallery.data.io.TestSchedulerProvider
import com.mygallery.data.DataManager
import com.mygallery.ui.main.MainPresenter
import com.mygallery.ui.main.MainPresenterImpl
import com.mygallery.ui.main.MainView
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 13/02/18
 * @Notes Tests for MainPresenterTests
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTests {

    @Mock
    lateinit var mockMainView: MainView

    @Mock
    lateinit var mockDataManager: DataManager

    @Mock
    lateinit var mockedContext: Context

    lateinit var mainPresenter : MainPresenter<MainView>
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

        mainPresenter = MainPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        mainPresenter.onAttach(mockMainView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mainPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnResume(){

    }

}