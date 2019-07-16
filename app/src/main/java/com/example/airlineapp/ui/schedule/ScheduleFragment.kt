package com.example.airlineapp.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.airlineapp.R
import com.example.airlineapp.databinding.FragmentScheduleBinding
import com.example.airlineapp.ui.LandingScreenActivity
import com.example.airlineapp.data.ScheduleInfo
import com.example.airlineapp.extensions.hide
import com.example.airlineapp.extensions.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.schedule_toolbar.view.*
import javax.inject.Inject

class ScheduleFragment : Fragment() {
    private lateinit var scheduleInfo: ScheduleInfo

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            scheduleInfo = it.getParcelable(SCHEDULE_LOCATION_TAG)
        }
        viewModel = ViewModelProviders.of(this, vmFactory)[ScheduleViewModel::class.java]
        registerObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpScheduleActionBar()
        bindScheduleListToAdapter()
        viewModel.fetchSchedules(scheduleInfo)
    }

    private fun registerObservers() {
        viewModel.totalSchedules.observe(this, Observer { onTotalSchedulesReceived(it) })
        viewModel.errorMsg.observe(this, Observer { onErrorReceived(it) })
    }

    private fun onErrorReceived(it: String?) {
        progressLoader.hide()
        errorLabel.show()
        errorLabel.text = it
    }

    private fun onTotalSchedulesReceived(it: String?) {
        progressLoader.hide()
        flightDetailsGroup.show()
        totalSchedules.text = it
    }

    private fun bindScheduleListToAdapter() {
        scheduleList.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        scheduleList.adapter = ScheduleAdapter(scheduleInfo)
    }

    private fun setUpScheduleActionBar() {
        val landingScreenActivity = (activity as LandingScreenActivity)
        val toolbar = scheduleToolbar as Toolbar
        landingScreenActivity.supportActionBar?.hide()
        landingScreenActivity.setSupportActionBar(toolbar)
        setToolbarCodeLabelText(toolbar)
        scheduleToolbar.image.setOnClickListener {
            landingScreenActivity.supportFragmentManager.popBackStack()
        }
    }

    private fun setToolbarCodeLabelText(toolbar: Toolbar) {
        toolbar.originLabel.text = scheduleInfo.origin.label
        toolbar.originCode.text = scheduleInfo.origin.code

        toolbar.destinationLabel.text = scheduleInfo.destination.label
        toolbar.destinationCode.text = scheduleInfo.destination.code
    }

    companion object {
        const val SCHEDULE_LOCATION_TAG = "scheduleInfo"
    }
}
