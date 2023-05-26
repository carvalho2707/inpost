package pl.inpost.recruitmenttask.shipment.domain.generator

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.shipment.domain.model.Header
import pl.inpost.recruitmenttask.shipment.domain.model.PickUp
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import pl.inpost.recruitmenttask.labels.R as LabelsR

fun generateShipment(
    number: String = "1",
    @StringRes status: Int = LabelsR.string.status_delivered,
    email: String = "adresmailowy@mail.pl",
    pickUp: PickUp? = generatePickUp()
) = Shipment(
    number = number,
    status = status,
    email = email,
    pickUp = pickUp
)

fun generateHeader(@StringRes title: Int = LabelsR.string.status_ready_to_pickup) = Header(title)

fun generatePickUp() = PickUp(
    dayOfTheWeek = "Tue.",
    date = "10.10.23",
    time = "10:00"
)
