package id.co.iconpln.insidaltt.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import id.co.iconpln.insidaltt.R
import id.co.iconpln.insidaltt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializing()
        bindView()
    }

    private fun bindView() {
        initToolbar("Main Insidal")
    }

    private fun initializing() {

    }

    override fun initToolbar(title: String) {
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun getContext(): Context {
        return this
    }

    override fun <T : Class<S>, S : Activity> navigateToActivity(classActivity: T) {
        val i = Intent(this@MainActivity, classActivity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}
