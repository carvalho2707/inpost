package pl.inpost.recruitmenttask.shipment.data.repository

import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.generator.generateShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.local.dao.ShipmentEntityDao
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment

internal class ShipmentsRepositoryTest {

    private val shipmentApi = mockk<ShipmentApi>()
    private val archivedShipmentDao = mockk<ArchivedShipmentDao>()
    private val shipmentEntityDao = mockk<ShipmentEntityDao> {
        coJustRun { insertAll(any()) }
    }

    lateinit var underTest: ShipmentsRepository

    @Test
    fun `given empty archived shipments when getShipments then should return all shipments`() =
        runBlocking {
            val shipments = listOf(
                generateShipmentNetwork()
            )

            coEvery { archivedShipmentDao.getAll() } returns emptyList()
            coEvery { shipmentApi.getShipments() } returns shipments

            underTest = ShipmentsRepository(shipmentApi, archivedShipmentDao, shipmentEntityDao)

            val result = underTest.getShipments()

            assertEquals(shipments, result)

            coVerify(exactly = 1) { archivedShipmentDao.getAll() }
            coVerify(exactly = 1) { shipmentEntityDao.insertAll(any()) }
            coVerify(exactly = 1) { shipmentApi.getShipments() }
        }

    @Test
    fun `given archived shipments when getShipments then should return filtered shipments`() =
        runBlocking {
            val archivedShipments = listOf(
                ArchivedShipment("1")
            )

            val shipment1 = generateShipmentNetwork(number = "1")
            val shipment2 = generateShipmentNetwork(number = "2")

            val shipments = listOf(
                shipment1,
                shipment2
            )

            val expected = listOf(shipment2)

            coEvery { archivedShipmentDao.getAll() } returns archivedShipments
            coEvery { shipmentApi.getShipments() } returns shipments

            underTest = ShipmentsRepository(shipmentApi, archivedShipmentDao, shipmentEntityDao)

            val result = underTest.getShipments()

            assertEquals(expected, result)

            coVerify(exactly = 1) { archivedShipmentDao.getAll() }
            coVerify(exactly = 1) { shipmentApi.getShipments() }
            coVerify(exactly = 1) { shipmentEntityDao.insertAll(any()) }
        }

    @Test
    fun `when archiveShipment then should archive shipment`() =
        runBlocking {
            val input = "1000"
            val archivedShipment = ArchivedShipment(input)

            coJustRun { archivedShipmentDao.insert(any()) }

            underTest = ShipmentsRepository(shipmentApi, archivedShipmentDao, shipmentEntityDao)

            underTest.archiveShipment(input)

            coVerify(exactly = 0) { archivedShipmentDao.getAll() }
            coVerify(exactly = 0) { shipmentApi.getShipments() }
            coVerify(exactly = 1) { archivedShipmentDao.insert(archivedShipment) }
        }
}
