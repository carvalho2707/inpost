package pl.inpost.recruitmenttask.shipment.data.repository

import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment
import javax.inject.Inject

class ShipmentsRepository @Inject constructor(
    private val shipmentApi: ShipmentApi,
    private val archivedShipmentDao: ArchivedShipmentDao
) {

    suspend fun getShipments(): List<ShipmentNetwork> {
        val archivedShipments = archivedShipmentDao.getAll().map { it.orderNumber }
        return shipmentApi.getShipments()
            .filter { !archivedShipments.contains(it.number) }
    }

    suspend fun archiveShipment(orderNumber: String) {
        archivedShipmentDao.insert(ArchivedShipment(orderNumber))
    }

    suspend fun unarchiveShipment(orderNumber: String) {
        archivedShipmentDao.delete(ArchivedShipment(orderNumber))
    }
}
