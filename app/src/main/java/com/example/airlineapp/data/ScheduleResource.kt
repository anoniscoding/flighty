package com.example.airlineapp.data

import com.google.gson.annotations.SerializedName

class ScheduleResource {
    @SerializedName("Schedule")
    var schedule: List<Schedule> = emptyList()
}

class Schedule {
    @SerializedName("TotalJourney")
    var totalJourney: TotalJourney = TotalJourney()

    @SerializedName("Flight")
    var flights: List<Flight> = emptyList()

    fun hasMultipleStops() = flights.size > 1
}

class TotalJourney {
    @SerializedName("Duration")
    var duration: String = ""
}

class Flight {
    @SerializedName("Departure")
    var departure: Departure = Departure()

    @SerializedName("Arrival")
    var arrival: Arrival = Arrival()

    @SerializedName("MarketingCarrier")
    var marketingCarrier: MarketingCarrier = MarketingCarrier()

    @SerializedName("Details")
    var details: Details = Details()
}

class Departure {
    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocal = ScheduledTimeLocal()
}

class ScheduledTimeLocal {
    @SerializedName("DateTime")
    val dateTime: String = ""
}

class Arrival {
    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocal = ScheduledTimeLocal()
}

class MarketingCarrier {
    @SerializedName("FlightNumber")
    val flightNumber: String = ""
}

class Details {
    @SerializedName("Stops")
    var stops: Stops = Stops()
}

class Stops {
    @SerializedName("StopQuantity")
    var stopQuantity: Int = Int.MIN_VALUE
}