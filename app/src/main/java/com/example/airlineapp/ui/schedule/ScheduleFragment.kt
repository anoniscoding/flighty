package com.example.airlineapp.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.airlineapp.R
import com.example.airlineapp.databinding.FragmentScheduleBinding
import com.example.airlineapp.ui.LandingScreenActivity
import com.example.airlineapp.ui.home.HomeFragment.Companion.SCHEDULE_LOCATION_TAG
import com.example.airlineapp.ui.home.ScheduleLocation
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.schedule_toolbar.view.*
import javax.inject.Inject

class ScheduleFragment : Fragment() {
    private lateinit var scheduleLocation: ScheduleLocation

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            scheduleLocation = it.getParcelable(SCHEDULE_LOCATION_TAG)
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
        setUpScheduleActionBar()
        viewModel.fetchSchedules(scheduleLocation)
    }

    private fun setUpScheduleActionBar() {
        val landingScreenActivity = (activity as LandingScreenActivity)
        val toolbar = scheduleToolbar as Toolbar
        landingScreenActivity.supportActionBar?.hide()
        landingScreenActivity.setSupportActionBar(toolbar)
        scheduleToolbar.image.setOnClickListener {
            landingScreenActivity.supportFragmentManager.popBackStack()
        }
    }
}
