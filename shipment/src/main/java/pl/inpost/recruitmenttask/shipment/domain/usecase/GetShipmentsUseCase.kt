package pl.inpost.recruitmenttask.shipment.domain.usecase

import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.shipment.domain.mapper.sort
import pl.inpost.recruitmenttask.shipment.domain.mapper.toShipment
import pl.inpost.recruitmenttask.shipment.domain.model.Header
import pl.inpost.recruitmenttask.shipment.domain.model.ShipmentModel
import javax.inject.Inject
import pl.inpost.recruitmenttask.labels.R as LabelsR

class GetShipmentsUseCase @Inject constructor(
    private val shipmentsRepository: ShipmentsRepository
) {

    suspend operator fun invoke(): List<ShipmentModel> {
        val results = mutableListOf<ShipmentModel>()

        val groupedShipments = shipmentsRepository.getShipments()
            .sort()
            .groupBy { it.operations.highlight }
            .mapValues { it.value.map { shipmentNetwork -> shipmentNetwork.toShipment() } }

        if (groupedShipments.containsKey(true)) {
            results.add(Header(LabelsR.string.status_ready_to_pickup))
            results.addAll(groupedShipments.getValue(true))
        }

        if (groupedShipments.containsKey(false)) {
            results.add(Header(LabelsR.string.shipment_header_others))
            results.addAll(groupedShipments.getValue(false))
        }

        return results
    }
}
