package com.example.checklist.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.checklist.database.Task
import com.example.checklist.database.getDatabase
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val tasks: LiveData<List<Task>> = database.taskDao.getAllTasks()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

@Suppress("UNCHECKED_CAST")
class Factory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}