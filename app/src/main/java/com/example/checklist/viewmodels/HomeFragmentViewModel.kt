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
    private val _navigateToDetail = MutableLiveData<Long>()
    val navigatedToDetail: LiveData<Long> get() = _navigateToDetail

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onTaskClicked(task: Task) {
        _navigateToDetail.value = task.id
    }

    fun onNavigateToDetailComplete() {
        _navigateToDetail.value = null
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