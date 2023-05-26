package pl.inpost.recruitmenttask.shipment.domain.model

data class UiState(
    val shipments: List<ShipmentModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
