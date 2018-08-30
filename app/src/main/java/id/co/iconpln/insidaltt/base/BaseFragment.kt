package id.co.iconpln.insidaltt.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import id.co.iconpln.insidaltt.di.component.*
import id.co.iconpln.insidaltt.di.module.ActivityModule
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cc.cloudist.acplibrary.ACProgressFlower
import id.co.iconpln.insidaltt.App.Companion.app
import javax.inject.Inject
import id.co.iconpln.insidaltt.util.SimpleAlert

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
abstract class BaseFragment: Fragment(), BaseView {
    @Inject
    lateinit var progress: ACProgressFlower

    protected lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val base = activity as BaseActivity

        DaggerActivityComponent.builder()
                .appComponent(app().appComponent())
                .activityModule(ActivityModule(context))
                .build()
                .inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context != null) {
            this.mContext = context
        }

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

    override fun getContext(): Context {
        return mContext
    }

}