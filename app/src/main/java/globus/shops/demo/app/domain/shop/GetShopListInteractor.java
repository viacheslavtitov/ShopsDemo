package globus.shops.demo.app.domain.shop;

import java.util.List;

import globus.shops.demo.app.domain.base.BaseInteractor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetShopListInteractor extends BaseInteractor<List<ShopEntity>, Void> {

    private final ShopListProvider shopListProvider;

    public GetShopListInteractor(Scheduler jobScheduler, Scheduler uiScheduler, ShopListProvider shopListProvider) {
        super(jobScheduler, uiScheduler);
        this.shopListProvider = shopListProvider;
    }

    @Override
    protected Observable<List<ShopEntity>> buildObservable(Void parameter) {
        return shopListProvider.getAllShops();
    }
}
