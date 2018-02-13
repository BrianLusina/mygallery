import android.content.Context
import com.mygallery.data.DataManager
import com.mygallery.data.io.TestSchedulerProvider
import com.mygallery.ui.main.MainView
import com.mygallery.ui.splash.SplashPresenter
import com.mygallery.ui.splash.SplashPresenterImpl
import com.mygallery.ui.splash.SplashView
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 13/02/18
 * @Notes Tests for SplashPresenterTests
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTests {
    @Mock
    lateinit var mockSplashView: SplashView

    @Mock
    lateinit var mockDataManager: DataManager
    @Mock
    lateinit var mockedContext: Context

    lateinit var splashPresenter : SplashPresenter<SplashView>
    val testScheduler: TestScheduler by lazy { TestScheduler() }

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

        splashPresenter = SplashPresenterImpl(mockDataManager, compositeDisposable, testSchedulerProvider)
        splashPresenter.onAttach(mockSplashView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        splashPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    // TODO: YOUR TESTS COM HERE

}