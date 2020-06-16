package com.example.checklist.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.checklist.database.Task
import com.example.checklist.database.TaskDatabase
import com.example.checklist.database.getDatabase
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class AddTaskFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addTask(task: Task) {
        uiScope.launch {
            createEntry(task)
        }
    }

    private suspend fun createEntry(task: Task) {
        withContext(Dispatchers.IO) {
            if (database.taskDao.getTask(task.id) != null) {
                database.taskDao.update(task)
            } else {
                database.taskDao.addTask(task)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

@Suppress("UNCHECKED_CAST")
class AddTaskFragmentViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskFragmentViewModel::class.java)) {
            return AddTaskFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}