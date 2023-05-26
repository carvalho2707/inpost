package pl.inpost.recruitmenttask.shipment.utils

fun <T> compareByDescending(vararg selectors: (T) -> Comparable<*>?): Comparator<T> {
    return Comparator { b, a -> compareValuesBy(a, b, *selectors) }
}
