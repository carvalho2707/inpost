package pl.inpost.recruitmenttask.shipment.data.api.model

import org.threeten.bp.ZonedDateTime

data class EventLogNetwork(
    val name: String,
    val date: ZonedDateTime
)
