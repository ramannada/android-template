package id.co.iconpln.insidaltt.base


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cc.cloudist.acplibrary.ACProgressFlower
import id.co.iconpln.insidaltt.App.Companion.app
import id.co.iconpln.insidaltt.di.component.ActivityComponent
import id.co.iconpln.insidaltt.di.component.AppComponent
import id.co.iconpln.insidaltt.di.component.DaggerActivityComponent
import id.co.iconpln.insidaltt.di.module.ActivityModule
import id.co.iconpln.insidaltt.util.SimpleAlert
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BaseView {
    @Inject
    lateinit var progress: ACProgressFlower

    var activityComponent: ActivityComponent

    init {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(app().appComponent())
                .activityModule(ActivityModule(this))
                .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

    }

    override fun showProgressDialog() {
        progress.show()
    }

    override fun dismissProgressDialog() {
        progress.dismiss()
    }

    override fun showToast(message: String) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun showAlertDialog(message: String) {
        SimpleAlert(getContext(), message)
    }
}
