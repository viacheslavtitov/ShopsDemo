package globus.shops.demo.app.domain.shop.detail;

import globus.shops.demo.app.domain.base.BaseInteractor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetDetailShopInteractor extends BaseInteractor<ShopDetailEntity, Integer> {

    private final DetailShopProvider detailShopProvider;

    public GetDetailShopInteractor(Scheduler jobScheduler, Scheduler uiScheduler, DetailShopProvider detailShopProvider) {
        super(jobScheduler, uiScheduler);
        this.detailShopProvider = detailShopProvider;
    }

    @Override
    protected Observable<ShopDetailEntity> buildObservable(Integer parameter) {
        return detailShopProvider.getDetailShopById(parameter);
    }
}
