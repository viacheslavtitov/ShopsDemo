package globus.shops.demo.app.presentation.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import globus.shops.demo.app.data.shop.ShopListProviderImpl;
import globus.shops.demo.app.data.shop.detail.DetailShopProviderImpl;
import globus.shops.demo.app.domain.shop.ShopListProvider;
import globus.shops.demo.app.domain.shop.detail.DetailShopProvider;

@Module
public class DataModule {

    @Singleton
    @Provides
    public ShopListProvider provideShopListProvider() {
        return new ShopListProviderImpl();
    }

    @Singleton
    @Provides
    public DetailShopProvider provideDetailShopProvider() {
        return new DetailShopProviderImpl();
    }

}
