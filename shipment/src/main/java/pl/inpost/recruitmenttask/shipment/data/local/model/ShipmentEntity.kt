package pl.inpost.recruitmenttask.shipment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "shipment")
data class ShipmentEntity(
    @PrimaryKey val number: String,
    val shipmentType: String,
    val status: String,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiverEmail: String?,
    val senderEmail: String?,
    val highlight: Boolean
)
