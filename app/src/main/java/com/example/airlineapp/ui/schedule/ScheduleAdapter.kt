package com.example.airlineapp.ui.schedule

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.airlineapp.R
import com.example.airlineapp.data.Flight
import com.example.airlineapp.data.Schedule
import com.example.airlineapp.data.ScheduleLocation
import com.example.airlineapp.data.TotalJourney
import com.example.airlineapp.utils.BindableAdapter
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapePathModel
import kotlinx.android.synthetic.main.schedule_card_item.view.*

class ScheduleAdapter(private val _scheduleLocation: ScheduleLocation) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(),
    BindableAdapter<List<Schedule>> {

    private var schedules = emptyList<Schedule>()

    override fun setData(data: List<Schedule>?) {
        schedules = data ?: emptyList()
        if (schedules.isNotEmpty()) notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.schedule = schedules[position]
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    inner class ViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        var schedule: Schedule = Schedule()
            set (schedule) {
                field = schedule

                if (schedule.hasMultipleStops()) {
                    val departureFlight = schedule.flights.first()
                    val arrivalFlight = schedule.flights.last()
                    setCardDetails(departureFlight, schedule.totalJourney, arrivalFlight)
                } else {
                    setCardDetails(schedule.flights.first(), schedule.totalJourney)
                }
            }

        init {
            setCardBackground()
            view.viewBtn.setOnClickListener { print("Hello world") }
        }

        //arrivalFlight is also the departureFlight whenever the schedule has zero stops
        private fun setCardDetails(
            departureFlight: Flight,
            totalJourney: TotalJourney,
            arrivalFlight: Flight = departureFlight
        ) {
            view.flightDate.text = departureFlight.departure.scheduledTimeLocal.dateTime.substring(0, 10)
            view.startTime.text = departureFlight.departure.scheduledTimeLocal.dateTime.substring(11)
            view.departureAirportCode.text = _scheduleLocation.origin.code()
            view.flightDuration.text = totalJourney.duration.substring(2)
            view.noOfStops.text = "${arrivalFlight.details.stops.stopQuantity} stops"
            view.endTime.text = arrivalFlight.arrival.scheduledTimeLocal.dateTime.substring(11)
            view.arrivalAirportCode.text = _scheduleLocation.destination.code()
            view.flightNumber.text = departureFlight.marketingCarrier.flightNumber
        }

        private fun setCardBackground() {
            val radius = 87.0f
            val leftShapePathModel = ShapePathModel()
            leftShapePathModel.topRightCorner = RoundedCornerTreatment(radius)
            leftShapePathModel.bottomRightCorner = RoundedCornerTreatment(radius)
            val leftRoundedMaterialShape = MaterialShapeDrawable(leftShapePathModel).apply {
                setTint(ContextCompat.getColor(view.context, R.color.colorAccent))
                paintStyle = Paint.Style.FILL
            }
            view.setBackgroundDrawable(leftRoundedMaterialShape)
        }
    }
}