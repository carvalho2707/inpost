package pl.inpost.recruitmenttask.shipment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import pl.inpost.recruitmenttask.shipment.domain.model.UiState
import pl.inpost.recruitmenttask.shipment.domain.usecase.GetShipmentsUseCase
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val getShipmentsUseCase: GetShipmentsUseCase,
    private val shipmentsRepository: ShipmentsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val shipments = getShipmentsUseCase()
            _uiState.update {
                it.copy(
                    shipments = shipments,
                    isLoading = false
                )
            }
        }
    }

    fun archiveShipment(shipment: Shipment) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            shipmentsRepository.archiveShipment(shipment.number)
            refreshData()
        }
    }
}
