package id.co.iconpln.insidaltt.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener
import id.co.iconpln.insidaltt.R
import id.co.iconpln.insidaltt.base.BaseActivity
import id.co.iconpln.insidaltt.helper.ConstantHelper
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Response
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializing()
        bindView()
    }

    private fun bindView() {
        btn_login.setOnClickListener {
            loginPresenter.login()
        }
    }

    private fun initializing() {
        initToolbar("Login")
    }

    override fun initToolbar(title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun getContext(): Context {
        return this
    }

    override fun <T : Class<S>, S : Activity> navigateToActivity(classActivity: T) {
        val i = Intent(this@LoginActivity, classActivity)
        startActivity(i)
    }
}
