package id.co.iconpln.insidaltt.util

import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
class SharedData(private var sharedPreferences: SharedPreferences,
                 private var gson: Gson) {
    companion object {
        val TOKEN = "user_token"
    }

    fun save(objects: Objects, key: String) {
        sharedPreferences.edit()
                .putString(key, gson.toJson(objects))
                .apply()
    }

    fun remove(key: String) {
        sharedPreferences.edit()
                .remove(key)
                .apply()
    }

    fun <T: Any> get(key: String, tClass: Class<T>): T? {
        val json = sharedPreferences.getString(key, null) ?: return null

        return gson.fromJson(key, tClass)
    }
}