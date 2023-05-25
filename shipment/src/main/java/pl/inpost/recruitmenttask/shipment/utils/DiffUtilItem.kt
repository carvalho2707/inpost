package pl.inpost.recruitmenttask.shipment.utils

abstract class DiffUtilItem {

    open fun hasSameContents(o: Any): Boolean {
        return equals(o)
    }

    open fun areSameItem(o: DiffUtilItem): Boolean {
        return itemID() == o.itemID()
    }

    abstract fun itemID(): Any

}