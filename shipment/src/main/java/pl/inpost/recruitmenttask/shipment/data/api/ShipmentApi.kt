package pl.inpost.recruitmenttask.shipment.data.api

import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork

interface ShipmentApi {
    suspend fun getShipments(): List<ShipmentNetwork>
}
