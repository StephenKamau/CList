package com.example.checklist.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.checklist.R
import com.example.checklist.database.Task

//
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.checklist.database.Task
//
//@BindingAdapter("listData")
//fun bindRecycleView(recyclerView: RecyclerView, data: List<Task>?) {
//    val adapter = recyclerView.adapter as TasksAdapter
////    data?.let {
////        adapter.submitList(it)
////    }
//}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: Task?) {
    setImageResource(
        when (item?.isComplete) {
            true -> R.drawable.ic_baseline_assignment_turned_in_24
            else -> R.drawable.ic_baseline_assignment_24
        }
    )
}