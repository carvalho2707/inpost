package pl.inpost.recruitmenttask.shipment.domain.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import pl.inpost.recruitmenttask.shipment.data.generator.generateShipmentNetwork
import pl.inpost.recruitmenttask.shipment.domain.generator.generateShipment
import pl.inpost.recruitmenttask.shipment.domain.model.PickUp

internal class MapperTest {

    @Test
    fun `given ZoneDateTime when toPickUp then should map`() {
        val localDateTime = LocalDateTime.of(2023, 10, 10, 10, 0)
        val input = ZonedDateTime.of(localDateTime, ZoneOffset.UTC)

        val expected = PickUp(
            dayOfTheWeek = "Tue.",
            date = "10.10.23",
            time = "10:00"
        )

        val result = input.toPickUp()

        assertEquals(expected, result)
    }

    @Test
    fun `given ShipmentNetwork with null pickUpDate when toShipment then should map without PickUp`() {
        val input = generateShipmentNetwork()

        val expected = generateShipment(
            pickUp = null
        )

        val result = input.toShipment()

        assertEquals(expected, result)
    }

    @Test
    fun `given ShipmentNetwork with null receiver when toShipment then should map email empty`() {
        val input = generateShipmentNetwork(
            receiver = null
        )

        val expected = generateShipment(
            pickUp = null,
            email = ""
        )

        val result = input.toShipment()

        assertEquals(expected, result)
    }

    @Test
    fun `given ShipmentNetwork when toShipment then should map`() {
        val localDateTime = LocalDateTime.of(2023, 10, 10, 10, 0)
        val pickupDate = ZonedDateTime.of(localDateTime, ZoneOffset.UTC)

        val input = generateShipmentNetwork(
            pickupDate = pickupDate
        )

        val expected = generateShipment()

        val result = input.toShipment()

        assertEquals(expected, result)
    }

    @Test
    fun `given List of ShipmentNetwork when sort then should respect the sorting rules`() {
        val input = listOf(
            generateShipmentNetwork(number = "1", status = "CREATED"),
            generateShipmentNetwork(number = "2", status = "CONFIRMED"),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "3",
                pickupDate = ZonedDateTime.parse("2023-12-23T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "4",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "5",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
                expireDate = ZonedDateTime.parse("2023-12-21T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "6",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
                expireDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "7",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
                expireDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00"),
                storedDate = ZonedDateTime.parse("2023-12-19T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "8",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
                expireDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00"),
                storedDate = ZonedDateTime.parse("2023-12-18T10:15:30+01:00")
            ),
            generateShipmentNetwork(
                status = "CONFIRMED",
                number = "9",
                pickupDate = ZonedDateTime.parse("2023-12-22T10:15:30+01:00"),
                expireDate = ZonedDateTime.parse("2023-12-20T10:15:30+01:00"),
                storedDate = ZonedDateTime.parse("2023-12-18T10:15:30+01:00")
            )
        )

        val expected = listOf("1", "3", "5", "7", "9", "8", "6", "4", "2")

        val result = input.sort().map { it.number }

        assertEquals(expected, result)
    }
}
