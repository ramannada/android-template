package id.co.iconpln.insidaltt

import android.app.Application
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ConnectionQuality
import com.androidnetworking.interfaces.ConnectionQualityChangeListener
import id.co.iconpln.insidaltt.di.component.AppComponent
import id.co.iconpln.insidaltt.di.component.DaggerAppComponent
import id.co.iconpln.insidaltt.di.module.AppModule
import id.co.iconpln.insidaltt.entity.MyObjectBox
import io.objectbox.BoxStore
import okhttp3.OkHttpClient
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
class App : Application() {
    private val TAG = App::class.java.simpleName
    private lateinit var appComponent: AppComponent
    private lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        instance = this

        this.appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()

        this.boxStore = MyObjectBox.builder().androidContext(this@App).build()


        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        AndroidNetworking.initialize(applicationContext, appComponent.okHttpClient())
        AndroidNetworking.enableLogging()
        AndroidNetworking.setConnectionQualityChangeListener(object : ConnectionQualityChangeListener {
            override fun onChange(currentConnectionQuality: ConnectionQuality?, currentBandwidth: Int) {
                Log.d(TAG, "onChange: currentConnectionQuality : $currentConnectionQuality currentBandwidth : $currentBandwidth")
            }

        })
    }

    companion object {
        lateinit var instance: App

        @JvmStatic
        fun app() = instance
    }

    fun appComponent(): AppComponent {
        return appComponent
    }

    fun boxStore(): BoxStore {
        return boxStore
    }
}