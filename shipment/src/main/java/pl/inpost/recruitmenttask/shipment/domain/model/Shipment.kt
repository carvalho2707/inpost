package pl.inpost.recruitmenttask.shipment.domain.model

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.core.utils.DiffUtilItem

open class ShipmentModel(private val uuid: String) : DiffUtilItem() {
    override fun itemID() = uuid
}

data class Shipment(
    val number: String,
    @StringRes val status: Int,
    val email: String,
    val pickUp: PickUp?
) : ShipmentModel(number)

data class Header(
    @StringRes val title: Int
) : ShipmentModel(title.toString())

data class PickUp(
    val dayOfTheWeek: String,
    val date: String,
    val time: String
)
