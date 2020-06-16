package com.example.checklist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id=:key")
    fun getTask(key: Long): Task

    @Delete(entity = Task::class)
    fun deleteTasks(task: Task)

    @Insert
    fun addTask(task: Task)

    @Update(entity = Task::class)
    fun update(task: Task)

    @Query("DELETE FROM task")
    fun clear()
}