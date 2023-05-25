package pl.inpost.recruitmenttask.shipment.data.repository

import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment
import javax.inject.Inject

class ShipmentsRepository @Inject constructor(
    private val shipmentApi: ShipmentApi,
    private val shipmentDao: ArchivedShipmentDao
) {

    suspend fun getShipments(): List<ShipmentNetwork> {
        return shipmentApi.getShipments()
    }

    suspend fun archiveShipment(orderNumber: String) {
        shipmentDao.insert(ArchivedShipment(orderNumber))
    }

    suspend fun unarchiveShipment(orderNumber: String) {
        shipmentDao.delete(ArchivedShipment(orderNumber))
    }

}