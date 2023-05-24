package pl.inpost.recruitmenttask.shipment.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.setState(newState: T.() -> T) {
    value = newState(requireNotNull(value) { "Missing initial data" })
}
