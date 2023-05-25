package pl.inpost.recruitmenttask.shipment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.shipment.data.ApiTypeAdapter
import pl.inpost.recruitmenttask.shipment.data.api.MockShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi
import pl.inpost.recruitmenttask.shipment.data.local.dao.ArchivedShipmentDao
import pl.inpost.recruitmenttask.shipment.data.repository.ShipmentsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShipmentRepository(
        shipmentApi: ShipmentApi,
        archivedShipmentDao: ArchivedShipmentDao
    ) = ShipmentsRepository(shipmentApi, archivedShipmentDao)

}