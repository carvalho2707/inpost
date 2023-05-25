package pl.inpost.recruitmenttask.shipment.domain.mapper

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.domain.model.PickUp
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import pl.inpost.recruitmenttask.shipment.domain.model.ShipmentStatus

fun ShipmentNetwork.toShipment() = Shipment(
    number = number,
    status = ShipmentStatus.fromValue(status).nameRes,
    email = receiver?.email.orEmpty(),
    pickUp = pickUpDate?.toPickUp()
)

fun ZonedDateTime.toPickUp(): PickUp {
    val dayOfTheWeek = this.format(DateTimeFormatter.ofPattern("EEE"))
    val date = this.format(DateTimeFormatter.ofPattern("dd.mm.yy"))
    val time = this.format(DateTimeFormatter.ofPattern("hh:mm"))

    return PickUp(
        dayOfTheWeek = dayOfTheWeek,
        date = date,
        time = time
    )
}