package id.co.iconpln.insidaltt.repository

/**
 * Created by labibmuhajir on 30/08/18.
 * labibmuhajir@yahoo.com
 */
interface BaseRepository<T> {
    fun findAll(): List<T>

    fun findById(id: Long): T?

    fun save(entity: T): T?

    fun remove(entity: T)

    fun remove(id: Long)
}