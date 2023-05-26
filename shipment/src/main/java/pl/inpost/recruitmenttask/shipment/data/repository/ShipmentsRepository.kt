package pl.inpost.recruitmenttask.shipment.data.repository

import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.local.dao.ShipmentEntityDao
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment
import pl.inpost.recruitmenttask.shipment.data.mapper.toShipmentEntity
import javax.inject.Inject

class ShipmentsRepository @Inject constructor(
    private val shipmentApi: ShipmentApi,
    private val archivedShipmentDao: ArchivedShipmentDao,
    private val shipmentEntityDao: ShipmentEntityDao
) {

    /*
    There wasn't any specification if the purpose of saving the shipments in the database was to
    make the app offline first so I went to just store it.
 */
    suspend fun getShipments(): List<ShipmentNetwork> {
        val archivedShipments = archivedShipmentDao.getAll().map { it.orderNumber }

        val shipments = shipmentApi.getShipments()

        val shipmentEntities = shipments.map { it.toShipmentEntity() }
        shipmentEntityDao.insertAll(shipmentEntities)

        return shipments
            .filter { !archivedShipments.contains(it.number) }
    }

    suspend fun archiveShipment(orderNumber: String) {
        archivedShipmentDao.insert(ArchivedShipment(orderNumber))
    }
}
