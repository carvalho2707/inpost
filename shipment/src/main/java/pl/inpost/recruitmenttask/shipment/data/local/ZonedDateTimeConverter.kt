package pl.inpost.recruitmenttask.shipment.data.local

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeConverter {

    @TypeConverter
    fun fromString(dateString: String?): ZonedDateTime? {
        return dateString?.let { ZonedDateTime.parse(it) }
    }

    @TypeConverter
    fun toString(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.toString()
    }
}
