package com.example.udacity_capstone.data.room

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME


class Converters {
    val type = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter = Moshi.Builder().build().adapter<List<String>>(type)

    @TypeConverter
    fun listToJson(value: List<String>?) = adapter.toJson(value)

    @TypeConverter
    fun jsonToList(json: String) = adapter.fromJson(json)


    // TODO https://www.baeldung.com/java-datetimeformatter
    // TODO https://medium.com/androiddevelopers/room-time-2b4cf9672b98
    // TODO 2022-09-06T20:08:04+02:00

    /*@TypeConverter
    fun fromDateTimeTzISO(offsetDateTime: OffsetDateTime?): String? {
        return offsetDateTime?.format(DateTimeFormatter.RFC_1123_DATE_TIME)
    }

    @TypeConverter
    fun toDateTimeTzISO(zonedDateTimeString: String?): OffsetDateTime? {
        return OffsetDateTime.parse(zonedDateTimeString, ISO_OFFSET_DATE_TIME)
    }*/
}