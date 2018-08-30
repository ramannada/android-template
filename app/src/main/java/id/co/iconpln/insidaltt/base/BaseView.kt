package id.co.iconpln.insidaltt.base

import android.app.Activity
import android.content.Context

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
interface BaseView {
    fun initToolbar(title: String)

    fun showProgressDialog()

    fun dismissProgressDialog()

    fun showToast(message: String)

    fun showAlertDialog(message: String)

    fun getContext(): Context

    fun <T : Class<S>, S : Activity> navigateToActivity(classActivity: T)
}