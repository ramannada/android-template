package id.co.iconpln.insidaltt.repository

import io.objectbox.Box

/**
 * Created by labibmuhajir on 30/08/18.
 * labibmuhajir@yahoo.com
 */
abstract class AbstractRepository<T> : BaseRepository<T>{
    open lateinit var box: Box<T>
}