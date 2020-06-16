package com.example.checklist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.checklist.R
import com.example.checklist.database.Task
import com.example.checklist.databinding.FragmentAddTaskBinding
import com.example.checklist.viewmodels.AddTaskFragmentViewModel
import com.example.checklist.viewmodels.AddTaskFragmentViewModelFactory
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentAddTaskBinding>(
                inflater,
                R.layout.fragment_add_task,
                container,
                false
            )
        binding.lifecycleOwner = viewLifecycleOwner
        val application = requireNotNull(this.activity).application
        val viewModel = ViewModelProviders.of(this, AddTaskFragmentViewModelFactory(application))
            .get(AddTaskFragmentViewModel::class.java)
        binding.addTaskViewModel = viewModel
        val taskTitle = binding.taskTitle.editText?.text.toString()
        val taskDescription = binding.taskDescription.editText?.text.toString()
        val isComplete = binding.taskCheck.isChecked
        val dateCompleted = LocalDateTime.now().toString()
        val task = Task()
        task.taskTitle = taskTitle
        task.description = taskDescription
        task.dateCompleted = dateCompleted
        task.isComplete = isComplete
        viewModel.addTask(task)
        return binding.root
    }
}