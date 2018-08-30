package id.co.iconpln.insidaltt.ui.launcher

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.anthonycr.grant.PermissionsManager
import com.anthonycr.grant.PermissionsResultAction
import id.co.iconpln.insidaltt.R
import id.co.iconpln.insidaltt.base.BaseActivity
import id.co.iconpln.insidaltt.ui.login.LoginActivity
import id.co.iconpln.insidaltt.ui.main.MainActivity

class LauncherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        initializing()
    }

    private fun initializing() {
        checkPermission()
    }

    override fun initToolbar(title: String) {

    }

    override fun getContext(): Context {
        return this
    }

    override fun <T : Class<S>, S : Activity> navigateToActivity(classActivity: T) {
        val i = Intent(this@LauncherActivity, classActivity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }



    private fun checkPermission() {
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)

        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
                permission, object : PermissionsResultAction() {
            override fun onGranted() {
                navigateToActivity(LoginActivity::class.java)
                finish()
            }

            override fun onDenied(permission: String?) {
                showToast("Izin ditolak, tidak dapat membuka aplikasi.")
                finish()
            }
        })
    }
}
