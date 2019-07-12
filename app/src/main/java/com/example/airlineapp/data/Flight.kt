package com.example.airlineapp.data

import com.google.gson.annotations.SerializedName

class LuftansaSchedule {

    @SerializedName("Flight")
    var flights: List<Flight> = emptyList()
}

class Flight {
    @SerializedName("Departure")
    var departure: Departure = Departure()

    @SerializedName("Arrival")
    var arrival: Arrival = Arrival()

    @SerializedName("MarketingCarrier")
    var marketingCarrier: MarketingCarrier = MarketingCarrier()

    @SerializedName("OperatingCarrier")
    var operatingCarrier: OperatingCarrier = OperatingCarrier()

    @SerializedName("Departure")
    var equipment: Equipment = Equipment()

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

class Terminal {
    @SerializedName("Name")
    var name: Int = Int.MIN_VALUE
}

class MarketingCarrier {
    @SerializedName("AirlineID")
    var airlineID: String = ""

    @SerializedName("FlightNumber")
    var flightNumber: Int = Int.MIN_VALUE
}

class OperatingCarrier {
    @SerializedName("AirlineID")
    var airlineID: String = ""
}

class Equipment {
    @SerializedName("AircraftCode")
    var aircraftCode: Int = Int.MIN_VALUE
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