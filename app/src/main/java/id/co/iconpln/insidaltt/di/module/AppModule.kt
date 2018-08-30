package id.co.iconpln.insidaltt.di.module

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import id.co.iconpln.insidaltt.App
import id.co.iconpln.insidaltt.ui.login.LoginActivity
import id.co.iconpln.insidaltt.util.SharedData
import io.objectbox.BoxStore
import okhttp3.OkHttpClient
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
@Module
class AppModule(private var context: Context) {
    @Singleton
    @Provides
    fun context(): Context {
        return this.context
    }

    @Singleton
    @Provides
    fun properties(): Properties{
        val properties = Properties()

        try {
            properties.load(context.assets.open("app.properties"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return properties
    }

    @Singleton
    @Provides
    fun gson(): Gson{
        return Gson()
    }

    @Singleton
    @Provides
    fun sharedPreferences(): SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun sharedData(sharedPreferences: SharedPreferences, gson: Gson): SharedData{
        return SharedData(sharedPreferences, gson)
    }

    @Singleton
    @Provides
    fun okHttp(properties: Properties, sharedData: SharedData): OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(properties.getProperty("http.timeout.connect").toLong(), TimeUnit.SECONDS)
                .readTimeout(properties.getProperty("http.timeout.read").toLong(), TimeUnit.SECONDS)
                .writeTimeout(properties.getProperty("http.timeout.connect").toLong(), TimeUnit.SECONDS)
                .addInterceptor {
                    val request = it.request().newBuilder()
                    request.addHeader("Content/Type", "application/json")

                    val token = sharedData.get(SharedData.TOKEN, String::class.java)
                    if (token != null) {
                        request.addHeader("Authorization", token)
                    }

                    val respon = it.proceed(request.build())

                    if (respon.code() == 401) {
                        sharedData.remove(SharedData.TOKEN)
                        val i = Intent(context, LoginActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(i)
                    }

                    return@addInterceptor respon
                }
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Singleton
    @Provides
    fun boxStore(): BoxStore {
        return (context as App).boxStore()
    }
}