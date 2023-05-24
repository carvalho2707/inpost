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
import pl.inpost.recruitmenttask.shipment.data.model.ShipmentNetwork
import pl.inpost.recruitmenttask.shipment.utils.setState
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentApi: ShipmentApi
) : ViewModel() {

    private val mutableViewState = MutableLiveData<List<ShipmentNetwork>>(emptyList())
    val viewState: LiveData<List<ShipmentNetwork>> = mutableViewState

    init {
        refreshData()
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Main) {
            val shipments = shipmentApi.getShipments()
            mutableViewState.setState { shipments }
        }
    }
}
