package pl.inpost.recruitmenttask.shipment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import pl.inpost.recruitmenttask.shipment.domain.usecase.GetShipmentsUseCase
import pl.inpost.recruitmenttask.shipment.utils.setState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val getShipmentsUseCase: GetShipmentsUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<List<Shipment>>(emptyList())
    val viewState: LiveData<List<Shipment>> = mutableViewState

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.Main) {
            val shipments = getShipmentsUseCase()
            mutableViewState.setState { shipments }
            Timber.d(shipments.toString())
        }
    }
}
