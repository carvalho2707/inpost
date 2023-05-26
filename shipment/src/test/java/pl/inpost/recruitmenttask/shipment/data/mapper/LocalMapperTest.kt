package pl.inpost.recruitmenttask.shipment.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.recruitmenttask.shipment.data.generator.generateShipmentNetwork
import pl.inpost.recruitmenttask.shipment.data.local.model.ShipmentEntity

internal class LocalMapperTest {

    @Test
    fun `given ShipmentNetwork when toShipmentEntity then should map`() {
        val input = generateShipmentNetwork(
            pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
            expireDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00"),
            storedDate = ZonedDateTime.parse("2023-12-18T10:15:30+01:00")
        )

        val expected = ShipmentEntity(
            number = "1",
            shipmentType = "PARCEL_LOCKER",
            status = "DELIVERED",
            pickUpDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
            expiryDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00"),
            storedDate = ZonedDateTime.parse("2023-12-18T10:15:30+01:00"),
            receiverEmail = "adresmailowy@mail.pl",
            senderEmail = "adresmailowy@mail.pl",
            highlight = false
        )

        val result = input.toShipmentEntity()

        assertEquals(expected, result)
    }
}
