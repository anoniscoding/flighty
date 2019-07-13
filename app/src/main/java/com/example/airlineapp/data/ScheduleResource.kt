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
    @SerializedName("AirportCode")
    var airportCode: String = ""

    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocal = ScheduledTimeLocal()
}

class ScheduledTimeLocal {
    @SerializedName("DateTime")
    val dateTime: String = ""
}

class Arrival {
    @SerializedName("AirportCode")
    var airportCode: String = ""

    @SerializedName("ScheduledTimeLocal")
    var scheduledTimeLocal: ScheduledTimeLocal = ScheduledTimeLocal()
}

class MarketingCarrier {
    @SerializedName("AirlineID")
    val airlineID: String = ""

    @SerializedName("FlightNumber")
    val flightNumber: String = ""
}

class Details {
    @SerializedName("Stops")
    var stops: Stops = Stops()

    @SerializedName("DaysOfOperation")
    var daysOfOperation: Int = Int.MIN_VALUE

    @SerializedName("DatePeriod")
    var datePeriod: DatePeriod = DatePeriod()
}

class Stops {
    @SerializedName("StopQuantity")
    var stopQuantity: Int = Int.MIN_VALUE
}

class DatePeriod {
    @SerializedName("Effective")
    var effective: String = ""

    @SerializedName("Expiration")
    var expiration: String = ""
}