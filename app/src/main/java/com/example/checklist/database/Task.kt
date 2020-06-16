package com.example.checklist.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Task constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var taskTitle: String = "",
    var description: String = "",
    val dateCreated: String = LocalDateTime.now().toString(),
    var dateCompleted: String = "",
    var isComplete: Boolean = false
) {
    val dateSimpleFormatter get() = dateCreated.split("T").first()
}