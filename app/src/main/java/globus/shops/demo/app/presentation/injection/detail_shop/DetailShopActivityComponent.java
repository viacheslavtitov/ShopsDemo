package globus.shops.demo.app.presentation.injection.detail_shop;

import javax.inject.Singleton;

import dagger.Component;
import globus.shops.demo.app.presentation.detail_shop.DetailShopActivity;
import globus.shops.demo.app.presentation.injection.DataModule;
import globus.shops.demo.app.presentation.injection.DomainModule;
import globus.shops.demo.app.presentation.injection.detail_shop.DetailShopModule;

@Singleton
@Component(modules = {DomainModule.class, DataModule.class, DetailShopModule.class})
public interface DetailShopActivityComponent {
    void inject(DetailShopActivity activity);
}
