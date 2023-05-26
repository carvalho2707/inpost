package pl.inpost.recruitmenttask.shipment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.inpost.recruitmenttask.core.utils.setSafeOnClickListener
import pl.inpost.recruitmenttask.shipment.R
import pl.inpost.recruitmenttask.shipment.databinding.HeaderItemBinding
import pl.inpost.recruitmenttask.shipment.databinding.ShipmentItemBinding
import pl.inpost.recruitmenttask.shipment.domain.model.Header
import pl.inpost.recruitmenttask.shipment.domain.model.Shipment
import pl.inpost.recruitmenttask.shipment.domain.model.ShipmentModel

class ShipmentAdapter(
    private val onClickMore: (Shipment) -> Unit,
    private val onClickCurrier: (Shipment) -> Unit,
) : ListAdapter<ShipmentModel, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.shipment_item -> ShipmentViewHolder(view, onClickMore, onClickCurrier)
            R.layout.header_item -> HeaderViewHolder(view)
            else -> throw Throwable("ShipmentAdapter got unexpected viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is ShipmentViewHolder -> holder.bind(it as Shipment)
                is HeaderViewHolder -> holder.bind(it as Header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Shipment -> R.layout.shipment_item
            is Header -> R.layout.header_item
            else -> throw Throwable("ShipmentAdapter got unexpected type")
        }
    }

    class ShipmentViewHolder(
        view: View,
        private val onClickMore: (Shipment) -> Unit,
        private val onClickCurrier: (Shipment) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val binding: ShipmentItemBinding = ShipmentItemBinding.bind(view)

        fun bind(shipment: Shipment) {
            with(binding) {
                shipmentNumber.text = shipment.number
                shipmentStatus.setText(shipment.status)
                shipmentSender.text = shipment.email

                shipment.pickUp?.let {
                    pickupLayout.dayOfTheWeek.text = it.dayOfTheWeek
                    pickupLayout.date.text = it.date
                    pickupLayout.time.text = it.time
                }
                pickupLayout.root.isVisible = shipment.pickUp != null

                shipmentMoreContainer.setSafeOnClickListener {
                    onClickMore(shipment)
                }

                currierImage.setSafeOnClickListener {
                    onClickCurrier(shipment)
                }
            }
        }
    }

    class HeaderViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding: HeaderItemBinding = HeaderItemBinding.bind(view)

        fun bind(header: Header) {
            with(binding) {
                status.setText(header.title)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ShipmentModel>() {
            override fun areItemsTheSame(oldItem: ShipmentModel, newItem: ShipmentModel) =
                oldItem.areSameItem(newItem)

            override fun areContentsTheSame(
                oldItem: ShipmentModel,
                newItem: ShipmentModel
            ) = oldItem.hasSameContents(newItem)
        }
    }
}
