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
    val dayOfTheWeek = this.format(DateTimeFormatter.ofPattern("EEE."))
    val date = this.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
    val time = this.format(DateTimeFormatter.ofPattern("hh:mm"))

    return PickUp(
        dayOfTheWeek = dayOfTheWeek,
        date = date,
        time = time
    )
}

/*
    My understanding of the sorting requirement was that we should older first by status and then
    by each of the following dates.
    I also thought it could meant that we should order by whatever date was closer to the current
    time but I went with the first approach since I wasn't sure which one it should be.
 */
fun List<ShipmentNetwork>.sort() = sortedWith(
    compareBy<ShipmentNetwork> { ShipmentStatus.fromValue(it.status).priority }
        .thenByDescending { it.pickUpDate }
        .thenByDescending { it.expiryDate }
        .thenByDescending { it.storedDate }
        .thenByDescending { it.number }
)
