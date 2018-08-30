package id.co.iconpln.insidaltt.base

/**
 * Created by labibmuhajir on 29/08/18.
 * labibmuhajir@yahoo.com
 */
abstract class BasePresenter<T: BaseView> {
    abstract var view: T
}