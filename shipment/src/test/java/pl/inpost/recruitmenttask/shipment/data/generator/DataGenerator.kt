package pl.inpost.recruitmenttask.shipment.data.generator

import org.threeten.bp.ZonedDateTime
import pl.inpost.recruitmenttask.shipment.data.api.model.CustomerNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.EventLogNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.OperationsNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentType
import kotlin.random.Random

fun generateShipmentNetwork(
    number: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
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

private fun generateCustomerNetwork(
    email: String = "name@email.com",
    phoneNumber: String = "123 123 123",
    name: String = "Jan Kowalski"
) = CustomerNetwork(
    email = email,
    phoneNumber = phoneNumber,
    name = name
)

private fun generateOperationsNetwork(
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
