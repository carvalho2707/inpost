package pl.inpost.recruitmenttask.shipment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.inpost.recruitmenttask.shipment.data.local.ZonedDateTimeConverter
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.local.dao.ShipmentEntityDao
import pl.inpost.recruitmenttask.shipment.data.local.model.ArchivedShipment
import pl.inpost.recruitmenttask.shipment.data.local.model.ShipmentEntity

@Database(entities = [ArchivedShipment::class, ShipmentEntity::class], version = 1)
@TypeConverters(value = [ZonedDateTimeConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun archivedShipmentDao(): ArchivedShipmentDao

    abstract fun shipmentEntityDao(): ShipmentEntityDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}
