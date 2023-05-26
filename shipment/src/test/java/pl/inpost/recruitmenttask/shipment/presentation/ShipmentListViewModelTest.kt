package pl.inpost.recruitmenttask.shipment.presentation

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.shipment.domain.generator.generateHeader
import pl.inpost.recruitmenttask.shipment.domain.generator.generateShipment
import pl.inpost.recruitmenttask.shipment.domain.model.ShipmentModel
import pl.inpost.recruitmenttask.shipment.domain.model.UiState
import pl.inpost.recruitmenttask.shipment.domain.usecase.GetShipmentsUseCase
import pl.inpost.recruitmenttask.shipment.utils.MainDispatcherRule

internal class ShipmentListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getShipmentsUseCase = mockk<GetShipmentsUseCase>()
    private val shipmentsRepository = mockk<ShipmentsRepository>()
    lateinit var underTest: ShipmentListViewModel

    @Test
    fun `when init then should fetch shipments`() =
        runBlocking {
            val shipments: List<ShipmentModel> = listOf(
                generateHeader(),
                generateShipment()
            )

            coEvery { getShipmentsUseCase() } returns Result.success(shipments)

            underTest = ShipmentListViewModel(getShipmentsUseCase, shipmentsRepository)

            underTest.uiState.test {
                val expected = UiState(
                    shipments = shipments,
                    isLoading = false,
                    isError = false
                )
                assertEquals(expected, awaitItem())

                coVerify(exactly = 1) { getShipmentsUseCase() }
            }
        }

    @Test
    fun `when refreshData then should fetch shipments`() =
        runBlocking {
            val shipments: List<ShipmentModel> = listOf(
                generateHeader(),
                generateShipment()
            )

            coEvery { getShipmentsUseCase() } returns Result.success(emptyList())

            underTest = ShipmentListViewModel(getShipmentsUseCase, shipmentsRepository)

            underTest.uiState.test {
                assertEquals(UiState(shipments = emptyList()), awaitItem())

                coEvery { getShipmentsUseCase() } returns Result.success(shipments)

                underTest.refreshData()

                assertEquals(UiState(shipments = shipments), awaitItem())

                coVerify(exactly = 2) { getShipmentsUseCase() }
            }
        }

    @Test
    fun `given error when refreshData then should set isError`() =
        runBlocking {
            val exception = Exception("error_message")

            coEvery { getShipmentsUseCase() } returns Result.failure(exception)

            underTest = ShipmentListViewModel(getShipmentsUseCase, shipmentsRepository)

            underTest.uiState.test {
                underTest.refreshData()

                val expected = UiState(
                    shipments = emptyList(),
                    isLoading = false,
                    isError = true
                )

                assertEquals(expected, awaitItem())

                coVerify(exactly = 2) { getShipmentsUseCase() }
            }
        }

    @Test
    fun `when archiveShipment then should complete`() =
        runBlocking {
            val input = generateShipment()

            val shipments: List<ShipmentModel> = listOf(
                generateHeader(),
                generateShipment()
            )

            coEvery { getShipmentsUseCase() } returns Result.success(shipments)
            coJustRun { shipmentsRepository.archiveShipment(any()) }

            underTest = ShipmentListViewModel(getShipmentsUseCase, shipmentsRepository)

            underTest.archiveShipment(input)

            coVerify(exactly = 2) { getShipmentsUseCase() }
            coVerify(exactly = 1) { shipmentsRepository.archiveShipment(any()) }
        }
}
