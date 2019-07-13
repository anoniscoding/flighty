package com.example.airlineapp.ui.home

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.airlineapp.R
import com.example.airlineapp.data.LocationData
import com.example.airlineapp.data.ScheduleLocation
import com.example.airlineapp.databinding.HomeFragmentBinding
import com.example.airlineapp.ui.LandingScreenActivity
import com.example.airlineapp.ui.schedule.ScheduleFragment
import com.example.airlineapp.ui.schedule.ScheduleFragment.Companion.SCHEDULE_LOCATION_TAG
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapePathModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.landing_screen.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory)[HomeViewModel::class.java]
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpCustomActionBar()
        initLocationAdapters()
        changeSetFlightBtnBackground()
    }

    private fun changeSetFlightBtnBackground() {
        val leftShapePathModel = ShapePathModel()
        leftShapePathModel.topLeftCorner = RoundedCornerTreatment(BTN_RADIUS)
        leftShapePathModel.bottomLeftCorner = RoundedCornerTreatment(BTN_RADIUS)
        val leftRoundedMaterialShape = MaterialShapeDrawable(leftShapePathModel).apply {
            setTint(ContextCompat.getColor(context!!, R.color.colorPrimary))
            paintStyle = Paint.Style.FILL
        }
        searchFlightBtn.setBackgroundDrawable(leftRoundedMaterialShape)
    }

    private fun initLocationAdapters() {
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, LocationData.values())
        origin.setAdapter(adapter)
        destination.setAdapter(adapter)
    }

    private fun setUpCustomActionBar() {
        val landingScreenActivity = (activity as LandingScreenActivity)
        landingScreenActivity.supportActionBar?.hide()
        landingScreenActivity.setSupportActionBar(homeToolbar as Toolbar)
    }

    private fun observeLiveData() {
        viewModel.originError.observe(this, Observer { binding.origin.error = it })
        viewModel.destinationError.observe(this, Observer { binding.destination.error = it })
        viewModel.scheduleLocation.observe(this, Observer { onScheduleLocationReceived(it) })
    }

    private fun onScheduleLocationReceived(it: ScheduleLocation?) {
        val bundle = Bundle().apply {
            putParcelable(SCHEDULE_LOCATION_TAG, it)
        }

        moveToScheduleFragment(bundle)
    }

    private fun moveToScheduleFragment(bundle: Bundle) {
        val scheduleFragment = ScheduleFragment().apply { arguments = bundle }
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(it.fragment_container.id, scheduleFragment)
                .addToBackStack(null).commit()
        }
    }

    companion object {
        const val BTN_RADIUS = 85.0f
    }

}
