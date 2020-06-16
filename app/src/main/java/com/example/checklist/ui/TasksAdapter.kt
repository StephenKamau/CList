package com.example.checklist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.database.Task
import com.example.checklist.databinding.ChecklistItemHolderBinding

class TasksAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Task, TasksAdapter.TaskViewHolder>(DiffCallBack) {
    class TaskViewHolder(private var binding: ChecklistItemHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ChecklistItemHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(task)
        }
        holder.bind(task)
    }

    class OnClickListener(private val clickListener: (task: Task) -> Unit) {
        fun onClick(task: Task) = clickListener(task)
    }
}