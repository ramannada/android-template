package id.co.iconpln.insidaltt.di.component

import dagger.Component
import id.co.iconpln.insidaltt.base.BaseActivity
import id.co.iconpln.insidaltt.base.BaseFragment
import id.co.iconpln.insidaltt.di.module.ActivityModule
import id.co.iconpln.insidaltt.di.scope.PerActivity

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
@PerActivity
@Component(modules = [ActivityModule::class],
        dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(fragment: BaseFragment)

    fun inject(activity: BaseActivity)
}