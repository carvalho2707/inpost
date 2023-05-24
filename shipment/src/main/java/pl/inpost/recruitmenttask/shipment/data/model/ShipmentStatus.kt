package pl.inpost.recruitmenttask.shipment.data.model


import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.labels.R as LabelsR

/**
 * Order of statuses
 * 1. CREATED
 * 2. CONFIRMED
 * 3. ADOPTED_AT_SOURCE_BRANCH
 * 4. SENT_FROM_SOURCE_BRANCH
 * 5. ADOPTED_AT_SORTING_CENTER
 * 6. SENT_FROM_SORTING_CENTER
 * 7. OTHER
 * 8. DELIVERED
 * 9. RETURNED_TO_SENDER
 * 10. AVIZO
 * 11. OUT_FOR_DELIVERY
 * 12. READY_TO_PICKUP
 * 13. PICKUP_TIME_EXPIRED
 */
enum class ShipmentStatus(
    @StringRes val nameRes: Int
) {
    ADOPTED_AT_SORTING_CENTER(LabelsR.string.status_adopted_at_sorting_center),
    SENT_FROM_SORTING_CENTER(LabelsR.string.status_sent_from_sorting_center),
    ADOPTED_AT_SOURCE_BRANCH(LabelsR.string.status_adopted_at_source_branch),
    SENT_FROM_SOURCE_BRANCH(LabelsR.string.status_sent_from_source_branch),
    AVIZO(LabelsR.string.status_avizo),
    CONFIRMED(LabelsR.string.status_confirmed),
    CREATED(LabelsR.string.status_created),
    DELIVERED(LabelsR.string.status_delivered),
    OTHER(LabelsR.string.status_other),
    OUT_FOR_DELIVERY(LabelsR.string.status_out_for_delivery),
    PICKUP_TIME_EXPIRED(LabelsR.string.status_pickup_time_expired),
    READY_TO_PICKUP(LabelsR.string.status_ready_to_pickup),
    RETURNED_TO_SENDER(LabelsR.string.status_returned_to_sender);
}
