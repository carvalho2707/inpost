package pl.inpost.recruitmenttask.shipment.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.recruitmenttask.shipment.data.generator.generateOperationsNetwork
import pl.inpost.recruitmenttask.shipment.data.generator.generateShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.shipment.domain.generator.generateShipment
import pl.inpost.recruitmenttask.shipment.domain.model.Header
import pl.inpost.recruitmenttask.shipment.domain.model.ShipmentModel
import pl.inpost.recruitmenttask.labels.R as LabelsR

internal class GetShipmentsUseCaseTest {

    private val shipmentsRepository = mockk<ShipmentsRepository>()
    lateinit var underTest: GetShipmentsUseCase

    @Test
    fun `when invoke then should return all shipments`() =
        runBlocking {
            val operationsNetworkWithHighlight = generateOperationsNetwork(highlight = true)
            val operationsNetworkWithoutHighlight = generateOperationsNetwork(highlight = false)

            val shipments = listOf(
                generateShipmentNetwork(
                    number = "1",
                    status = "AVIZO",
                    operations = operationsNetworkWithHighlight
                ),
                generateShipmentNetwork(
                    number = "2",
                    status = "CREATED",
                    operations = operationsNetworkWithHighlight
                ),
                generateShipmentNetwork(
                    number = "3",
                    status = "AVIZO",
                    operations = operationsNetworkWithoutHighlight
                ),
                generateShipmentNetwork(
                    number = "4",
                    status = "CREATED",
                    operations = operationsNetworkWithoutHighlight
                )
            )

            coEvery { shipmentsRepository.getShipments() } returns shipments

            val expected = listOf(
                Header(LabelsR.string.status_ready_to_pickup),
                generateShipment("2", pickUp = null, status = LabelsR.string.status_created),
                generateShipment("1", pickUp = null, status = LabelsR.string.status_avizo),
                Header(LabelsR.string.shipment_header_others),
                generateShipment("4", pickUp = null, status = LabelsR.string.status_created),
                generateShipment("3", pickUp = null, status = LabelsR.string.status_avizo)
            )

            underTest = GetShipmentsUseCase(shipmentsRepository)

            val result = underTest()

            assertEquals(expected, result)

            coVerify(exactly = 1) { shipmentsRepository.getShipments() }
        }

    @Test
    fun `given no highlights true when invoke then should not return header`() =
        runBlocking {
            val operationsNetworkWithoutHighlight = generateOperationsNetwork(highlight = false)

            val shipments = listOf(
                generateShipmentNetwork(
                    number = "3",
                    status = "AVIZO",
                    operations = operationsNetworkWithoutHighlight
                )
            )

            coEvery { shipmentsRepository.getShipments() } returns shipments

            val expected = listOf(
                Header(LabelsR.string.shipment_header_others),
                generateShipment("3", pickUp = null, status = LabelsR.string.status_avizo)
            )

            underTest = GetShipmentsUseCase(shipmentsRepository)

            val result = underTest()

            assertEquals(expected, result)

            coVerify(exactly = 1) { shipmentsRepository.getShipments() }
        }

    @Test
    fun `given no highlights false when invoke then should not return header`() =
        runBlocking {
            val operationsNetworkWithHighlight = generateOperationsNetwork(highlight = true)

            val shipments = listOf(
                generateShipmentNetwork(
                    number = "2",
                    status = "CREATED",
                    operations = operationsNetworkWithHighlight
                )
            )

            coEvery { shipmentsRepository.getShipments() } returns shipments

            val expected = listOf(
                Header(LabelsR.string.status_ready_to_pickup),
                generateShipment("2", pickUp = null, status = LabelsR.string.status_created)
            )

            underTest = GetShipmentsUseCase(shipmentsRepository)

            val result = underTest()

            assertEquals(expected, result)

            coVerify(exactly = 1) { shipmentsRepository.getShipments() }
        }

    @Test
    fun `given no shipments false when invoke then should return empty list`() =
        runBlocking {
            coEvery { shipmentsRepository.getShipments() } returns emptyList()

            val expected = emptyList<ShipmentModel>()

            underTest = GetShipmentsUseCase(shipmentsRepository)

            val result = underTest()

            assertEquals(expected, result)

            coVerify(exactly = 1) { shipmentsRepository.getShipments() }
        }
}
