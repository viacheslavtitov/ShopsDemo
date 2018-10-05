package globus.shops.demo.app.presentation.injection.detail_shop;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import globus.shops.demo.app.domain.shop.detail.DetailShopProvider;
import globus.shops.demo.app.domain.shop.detail.GetDetailShopInteractor;
import globus.shops.demo.app.presentation.detail_shop.DetailShopPresenter;
import globus.shops.demo.app.presentation.injection.DomainModule;
import io.reactivex.Scheduler;

@Module
public class DetailShopModule {

    @Singleton
    @Provides
    public GetDetailShopInteractor provideGetShopListInteractor(@Named(DomainModule.JOB) Scheduler schedulerJob,
                                                                @Named(DomainModule.UI) Scheduler schedulerUI,
                                                                DetailShopProvider detailShopProvider) {
        return new GetDetailShopInteractor(schedulerJob, schedulerUI, detailShopProvider);
    }

    @Singleton
    @Provides
    public DetailShopPresenter provideDetailShopPresenter(GetDetailShopInteractor getDetailShopInteractor) {
        return new DetailShopPresenter(getDetailShopInteractor);
    }

}
