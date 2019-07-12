package com.example.airlineapp.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.airlineapp.R
import com.example.airlineapp.databinding.FragmentScheduleBinding
import javax.inject.Inject

private const val ORIGIN_LABEL = "origin"
private const val DESTINATION_LABEL = "destination"


class ScheduleFragment : Fragment() {
    private var origin: String = ""
    private var destination: String = ""

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            origin = it.getString(ORIGIN_LABEL) ?: ""
            destination = it.getString(DESTINATION_LABEL) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, vmFactory)[ScheduleViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.fetchSchedules(origin, destination)
    }


}
