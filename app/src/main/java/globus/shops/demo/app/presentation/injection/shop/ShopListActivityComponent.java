package globus.shops.demo.app.presentation.injection.shop;

import javax.inject.Singleton;

import dagger.Component;
import globus.shops.demo.app.presentation.shop.ShopListActivity;
import globus.shops.demo.app.presentation.injection.DataModule;
import globus.shops.demo.app.presentation.injection.DomainModule;

@Singleton
@Component(modules = {DomainModule.class, DataModule.class, ShopListModule.class})
public interface ShopListActivityComponent {
    void inject(ShopListActivity activity);
}
