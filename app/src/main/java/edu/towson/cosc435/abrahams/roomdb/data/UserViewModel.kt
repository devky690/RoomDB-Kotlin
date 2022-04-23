package edu.towson.cosc435.abrahams.roomdb.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : ViewModel() {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user)
        }
    }
}