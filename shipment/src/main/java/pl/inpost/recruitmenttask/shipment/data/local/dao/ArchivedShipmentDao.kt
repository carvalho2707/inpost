package pl.inpost.recruitmenttask.shipment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment

@Dao
interface ArchivedShipmentDao {

    @Query("SELECT * FROM archived_shipment")
    suspend fun getAll(): List<ArchivedShipment>

    @Insert
    suspend fun insert(archivedShipment: ArchivedShipment)

    @Delete
    suspend fun delete(archivedShipment: ArchivedShipment)

}