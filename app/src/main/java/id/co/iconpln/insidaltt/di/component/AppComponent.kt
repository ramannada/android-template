package id.co.iconpln.insidaltt.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Component
import id.co.iconpln.insidaltt.di.module.AppModule
import id.co.iconpln.insidaltt.util.SharedData
import io.objectbox.BoxStore
import okhttp3.OkHttpClient
import java.util.*
import javax.inject.Singleton

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context

    fun okHttpClient(): OkHttpClient

    fun properties(): Properties

    fun gson(): Gson

    fun sharedData(): SharedData

    fun boxStore(): BoxStore
}