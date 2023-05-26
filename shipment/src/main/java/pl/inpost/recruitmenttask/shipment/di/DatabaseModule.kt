package pl.inpost.recruitmenttask.shipment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.shipment.AppDatabase
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Provides
    fun provideArchivedShipmentDao(appDatabase: AppDatabase): ArchivedShipmentDao {
        return appDatabase.archivedShipmentDao()
    }
}
