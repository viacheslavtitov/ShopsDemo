package globus.shops.demo.app.presentation.injection.shop;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import globus.shops.demo.app.domain.shop.GetShopListInteractor;
import globus.shops.demo.app.domain.shop.ShopListProvider;
import globus.shops.demo.app.presentation.injection.DomainModule;
import globus.shops.demo.app.presentation.shop.ShopListPresenter;
import io.reactivex.Scheduler;

@Module
public class ShopListModule {

    @Singleton
    @Provides
    public GetShopListInteractor provideGetShopListInteractor(@Named(DomainModule.JOB) Scheduler schedulerJob,
                                                              @Named(DomainModule.UI) Scheduler schedulerUI,
                                                              ShopListProvider shopListProvider) {
        return new GetShopListInteractor(schedulerJob, schedulerUI, shopListProvider);
    }

    @Singleton
    @Provides
    public ShopListPresenter provideShopListPresenter(GetShopListInteractor getShopListInteractor) {
        return new ShopListPresenter(getShopListInteractor);
    }

}
