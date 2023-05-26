package pl.inpost.recruitmenttask.shipment.presentation

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
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
    lateinit var underTest: ShipmentListViewModel

    @Test
    fun `when init then should fetch shipments`() =
        runBlocking {
            val shipments: List<ShipmentModel> = listOf(
                generateHeader(),
                generateShipment()
            )

            coEvery { getShipmentsUseCase() } returns shipments

            underTest = ShipmentListViewModel(getShipmentsUseCase)

            underTest.uiState.test {
                val expected = UiState(shipments = shipments)
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

            coEvery { getShipmentsUseCase() } returns emptyList()

            underTest = ShipmentListViewModel(getShipmentsUseCase)

            underTest.uiState.test {
                assertEquals(UiState(shipments = emptyList()), awaitItem())

                coEvery { getShipmentsUseCase() } returns shipments

                underTest.refreshData()

                assertEquals(UiState(shipments = shipments), awaitItem())

                coVerify(exactly = 2) { getShipmentsUseCase() }
            }
        }
}
