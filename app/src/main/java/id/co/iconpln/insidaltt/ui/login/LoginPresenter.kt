package id.co.iconpln.insidaltt.ui.login

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener
import id.co.iconpln.insidaltt.base.BasePresenter
import id.co.iconpln.insidaltt.base.BaseView
import id.co.iconpln.insidaltt.helper.ConstantHelper
import okhttp3.Response
import org.json.JSONObject

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
class LoginPresenter : BasePresenter<LoginView>() {
    override var view: LoginView
        get() = view
        set(view) {}

    fun login() {
        AndroidNetworking.get(ConstantHelper.ApiEndPoint.base_url + ConstantHelper.ApiEndPoint.online)
                .setTag("test onlilne")
                .setPriority(Priority.LOW)
                .build()
                .getAsOkHttpResponseAndJSONObject(object : OkHttpResponseAndJSONObjectRequestListener {
                    override fun onResponse(okHttpResponse: Response?, response: JSONObject?) {
                        view.showToast(okHttpResponse?.message()!!)
                    }

                    override fun onError(anError: ANError?) {
                        view.showToast(anError?.message!!)
                    }

                })
    }
}