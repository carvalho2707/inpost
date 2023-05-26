package pl.inpost.recruitmenttask.shipment.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

internal class ShipmentStatusTest {

    @Test
    fun `given unknown status when fromValue then should map to OTHER`() {
        assertEquals(ShipmentStatus.OTHER, ShipmentStatus.fromValue("UNKNOWN"))
    }

    @Test
    fun `when fromValue then should map`() {
        assertEquals(ShipmentStatus.ADOPTED_AT_SORTING_CENTER, ShipmentStatus.fromValue("ADOPTED_AT_SORTING_CENTER"))
        assertEquals(ShipmentStatus.SENT_FROM_SORTING_CENTER, ShipmentStatus.fromValue("SENT_FROM_SORTING_CENTER"))
        assertEquals(ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH, ShipmentStatus.fromValue("ADOPTED_AT_SOURCE_BRANCH"))
        assertEquals(ShipmentStatus.SENT_FROM_SOURCE_BRANCH, ShipmentStatus.fromValue("SENT_FROM_SOURCE_BRANCH"))
        assertEquals(ShipmentStatus.AVIZO, ShipmentStatus.fromValue("AVIZO"))
        assertEquals(ShipmentStatus.CONFIRMED, ShipmentStatus.fromValue("CONFIRMED"))
        assertEquals(ShipmentStatus.CREATED, ShipmentStatus.fromValue("CREATED"))
        assertEquals(ShipmentStatus.DELIVERED, ShipmentStatus.fromValue("DELIVERED"))
        assertEquals(ShipmentStatus.OTHER, ShipmentStatus.fromValue("OTHER"))
        assertEquals(ShipmentStatus.OUT_FOR_DELIVERY, ShipmentStatus.fromValue("OUT_FOR_DELIVERY"))
        assertEquals(ShipmentStatus.PICKUP_TIME_EXPIRED, ShipmentStatus.fromValue("PICKUP_TIME_EXPIRED"))
        assertEquals(ShipmentStatus.READY_TO_PICKUP, ShipmentStatus.fromValue("READY_TO_PICKUP"))
        assertEquals(ShipmentStatus.RETURNED_TO_SENDER, ShipmentStatus.fromValue("RETURNED_TO_SENDER"))
    }
}
