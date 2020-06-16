package com.example.checklist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.checklist.R
import com.example.checklist.databinding.FragmentHomeBinding
import com.example.checklist.viewmodels.Factory
import com.example.checklist.viewmodels.HomeFragmentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )

        val application = requireNotNull(this.activity).application
        val viewModel =
            ViewModelProviders.of(this, Factory(application)).get(HomeFragmentViewModel::class.java)
        binding.viewModel = viewModel

        binding.fab.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddTaskFragment2(0L)
            )
        }

        val adapter = TasksAdapter(
            TasksAdapter.OnClickListener { task ->
                viewModel.onTaskClicked(task)
            }
        )

        binding.checklist.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigatedToDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToAddTaskFragment2(it)
                )
                viewModel.onNavigateToDetailComplete()
            }
        })

        return binding.root
    }

}