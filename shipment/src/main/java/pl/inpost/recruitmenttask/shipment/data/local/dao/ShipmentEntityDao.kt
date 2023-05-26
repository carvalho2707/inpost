package pl.inpost.recruitmenttask.shipment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.inpost.recruitmenttask.shipment.data.local.model.ShipmentEntity

@Dao
interface ShipmentEntityDao {

    @Query("SELECT * FROM shipment")
    suspend fun getAll(): List<ShipmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shipments: List<ShipmentEntity>)
}
