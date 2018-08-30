package id.co.iconpln.insidaltt.di.module

import android.content.Context
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import dagger.Module
import dagger.Provides

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
@Module
class ActivityModule(private var context: Context) {
    @Provides
    fun acProgressFlower(): ACProgressFlower {
        return ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY)
                .build()
    }
}