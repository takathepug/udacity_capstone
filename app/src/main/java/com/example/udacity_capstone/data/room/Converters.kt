package com.example.udacity_capstone.data.room

import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME


class Converters {
    // TODO https://www.baeldung.com/java-datetimeformatter
    // TODO https://medium.com/androiddevelopers/room-time-2b4cf9672b98
    // TODO 2022-09-06T20:08:04+02:00

    @TypeConverter
    fun fromDateTimeTzISO(offsetDateTime: OffsetDateTime?): String? {
        return offsetDateTime?.format(DateTimeFormatter.RFC_1123_DATE_TIME)
    }

    @TypeConverter
    fun toDateTimeTzISO(zonedDateTimeString: String?): OffsetDateTime? {
        return OffsetDateTime.parse(zonedDateTimeString, ISO_OFFSET_DATE_TIME)
    }
}