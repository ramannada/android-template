package id.co.iconpln.insidaltt.repository

import id.co.iconpln.insidaltt.entity.User
import io.objectbox.Box
import io.objectbox.kotlin.query
import id.co.iconpln.insidaltt.entity.User_

/**
 * Created by labibmuhajir on 30/08/18.
 * labibmuhajir@yahoo.com
 */
class UserRepository (override var box: Box<User>) : AbstractRepository<User>() {
    override fun findAll(): List<User> {
        return box.all
    }

    override fun findById(id: Long): User? {
        return box.query{User_.id; id}.findFirst()
    }

    override fun save(entity: User): User? {
        return findById(box.put(entity))
    }

    override fun remove(entity: User) {
        box.remove(entity)
    }

    override fun remove(id: Long) {
        box.remove(id)
    }
}