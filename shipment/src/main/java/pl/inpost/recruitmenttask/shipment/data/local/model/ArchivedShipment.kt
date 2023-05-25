package pl.inpost.recruitmenttask.shipment.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archived_shipment")
data class ArchivedShipment(
    @PrimaryKey @ColumnInfo(name = "order_number") val orderNumber: String
)
