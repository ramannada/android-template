package id.co.iconpln.insidaltt.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by labibmuhajir on 30/08/18.
 * labibmuhajir@yahoo.com
 */
@Entity
class User {
    @Id
    var id: Long = 0
    lateinit var username: String
    lateinit var password: String
}