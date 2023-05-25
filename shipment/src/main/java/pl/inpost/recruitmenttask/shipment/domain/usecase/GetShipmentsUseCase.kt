package pl.inpost.recruitmenttask.shipment.domain.usecase

import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.shipment.domain.mapper.toShipment
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import javax.inject.Inject

class GetShipmentsUseCase @Inject constructor(
    private val shipmentsRepository: ShipmentsRepository
) {

    suspend operator fun invoke(): List<Shipment> {
        return shipmentsRepository.getShipments()
            .map { it.toShipment() }
    }

}