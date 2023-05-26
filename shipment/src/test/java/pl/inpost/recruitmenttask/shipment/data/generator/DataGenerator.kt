package pl.inpost.recruitmenttask.shipment.data.generator

import org.threeten.bp.ZonedDateTime
import pl.inpost.recruitmenttask.shipment.data.api.model.CustomerNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.EventLogNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.OperationsNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentType

fun generateShipmentNetwork(
    number: String = "1",
    type: ShipmentType = ShipmentType.PARCEL_LOCKER,
    status: String = "DELIVERED",
    sender: CustomerNetwork? = generateCustomerNetwork(),
    receiver: CustomerNetwork? = generateCustomerNetwork(),
    operations: OperationsNetwork = generateOperationsNetwork(),
    eventLog: List<EventLogNetwork> = emptyList(),
    openCode: String? = null,
    expireDate: ZonedDateTime? = null,
    storedDate: ZonedDateTime? = null,
    pickupDate: ZonedDateTime? = null
) = ShipmentNetwork(
    number = number,
    shipmentType = type.name,
    status = status,
    eventLog = eventLog,
    openCode = openCode,
    expiryDate = expireDate,
    storedDate = storedDate,
    pickUpDate = pickupDate,
    receiver = receiver,
    sender = sender,
    operations = operations
)

fun generateCustomerNetwork(
    email: String = "adresmailowy@mail.pl",
    phoneNumber: String = "123 123 123",
    name: String = "Jan Kowalski"
) = CustomerNetwork(
    email = email,
    phoneNumber = phoneNumber,
    name = name
)

fun generateOperationsNetwork(
    manualArchive: Boolean = false,
    delete: Boolean = false,
    collect: Boolean = false,
    highlight: Boolean = false,
    expandAvizo: Boolean = false,
    endOfWeekCollection: Boolean = false
) = OperationsNetwork(
    manualArchive = manualArchive,
    delete = delete,
    collect = collect,
    highlight = highlight,
    expandAvizo = expandAvizo,
    endOfWeekCollection = endOfWeekCollection
)
