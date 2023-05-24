package pl.inpost.recruitmenttask.shipment.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.shipment.data.ApiTypeAdapter
import pl.inpost.recruitmenttask.shipment.data.api.MockShipmentApi
import pl.inpost.recruitmenttask.shipment.data.api.ShipmentApi

@InstallIn(SingletonComponent::class)
@Module
class NetworkAndroidModule {

    @Provides
    fun shipmentApi(@ApplicationContext context: Context, apiTypeAdapter: ApiTypeAdapter): ShipmentApi = MockShipmentApi(context, apiTypeAdapter)
}
