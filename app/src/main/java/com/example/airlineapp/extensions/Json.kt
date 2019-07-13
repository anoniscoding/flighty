package com.example.airlineapp.extensions

import com.example.airlineapp.data.Schedule
import com.google.gson.*
import java.lang.reflect.Type

const val SCHEDULE_RESOURCE_LABEL = "ScheduleResource"

fun <T> JsonObject.convertTo(classOfT: Class<T>) : T? {
    val builder =  GsonBuilder()
    builder.registerTypeAdapter(Schedule::class.java, ScheduleDeserializer())

    return builder.create().fromJson(get(SCHEDULE_RESOURCE_LABEL), classOfT)
}

class ScheduleDeserializer : JsonDeserializer<Schedule> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type,
        context: JsonDeserializationContext
    ): Schedule {

        if (json.asJsonObject.get(FLIGHT_LABEL).isJsonObject) {
            convertFlightObjToFlightArray(json)
        }

        return Gson().fromJson(json, Schedule::class.java)
    }

    private fun convertFlightObjToFlightArray(json: JsonElement) {
        val flightArray = JsonArray()
        flightArray.add(json.asJsonObject.get(FLIGHT_LABEL).asJsonObject)
        json.asJsonObject.add(FLIGHT_LABEL, flightArray.asJsonArray)
    }

    companion object {
        const val FLIGHT_LABEL = "Flight"
    }
}