package edu.towson.cosc435.abrahams.roomdb.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao){

    val readAllData: LiveData<List<User>>

    init {
        readAllData = userDao.readAllData()
    }
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}