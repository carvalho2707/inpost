package pl.inpost.recruitmenttask.shipment.data.mapper

import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.model.ShipmentEntity

fun ShipmentNetwork.toShipmentEntity() = ShipmentEntity(
    number = number,
    shipmentType = shipmentType,
    status = status,
    expiryDate = expiryDate,
    storedDate = storedDate,
    pickUpDate = pickUpDate,
    receiverEmail = receiver?.email,
    senderEmail = sender?.email,
    highlight = operations.highlight
)
