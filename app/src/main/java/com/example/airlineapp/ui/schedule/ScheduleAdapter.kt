package com.example.airlineapp.ui.schedule

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.airlineapp.R
import com.example.airlineapp.data.Schedule
import com.example.airlineapp.data.ScheduleLocation
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
        this.schedules = data ?: emptyList()
        notifyDataSetChanged()
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
            set (value) {
                field = value

                if (value.flights.size == 1) {
                    view.flightDate.text = value.flights[0].departure.scheduledTimeLocal.dateTime.substring(0, 10)
                    view.startTime.text = value.flights[0].departure.scheduledTimeLocal.dateTime.substring(11)
                    view.departureAirportCode.text = _scheduleLocation.origin.code()
                    view.flightDuration.text = value.totalJourney.duration.substring(2)
                    view.noOfStops.text = "${value.flights[0].details.stops.stopQuantity} stops"
                    view.endTime.text = value.flights[0].arrival.scheduledTimeLocal.dateTime.substring(11)
                    view.arrivalAirportCode.text = _scheduleLocation.destination.code()
                    view.flightNumber.text = value.flights[0].marketingCarrier.flightNumber
                } else {
                    val firstFlight = value.flights[0]
                    val lastFlight = value.flights[value.flights.size - 1]
                    view.flightDate.text = firstFlight.departure.scheduledTimeLocal.dateTime.substring(0, 10)
                    view.startTime.text = firstFlight.departure.scheduledTimeLocal.dateTime.substring(11)
                    view.departureAirportCode.text = _scheduleLocation.origin.code()
                    view.flightDuration.text = value.totalJourney.duration.substring(2)
                    view.noOfStops.text = "${lastFlight.details.stops.stopQuantity} stops"
                    view.endTime.text = lastFlight.arrival.scheduledTimeLocal.dateTime.substring(11)
                    view.arrivalAirportCode.text = _scheduleLocation.destination.code()
                    view.flightNumber.text = firstFlight.marketingCarrier.flightNumber
                }

            }

        private fun changeBackground() {
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

        init {
            changeBackground()
            view.viewBtn.setOnClickListener {
                print("Hello world")
            }
        }



    }
}