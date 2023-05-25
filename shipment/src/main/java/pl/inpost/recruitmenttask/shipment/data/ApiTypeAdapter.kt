package pl.inpost.recruitmenttask.shipment.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dagger.Reusable
import javax.inject.Inject
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

@Reusable
class ApiTypeAdapter @Inject constructor() {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @FromJson
    fun toZonedDateTime(value: String): ZonedDateTime = formatter.parse(value, ZonedDateTime::from)

    @ToJson
    fun fromZonedDateTime(date: ZonedDateTime?): String? = date?.format(formatter)
}
