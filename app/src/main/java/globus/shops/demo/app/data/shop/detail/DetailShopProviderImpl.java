package globus.shops.demo.app.data.shop.detail;

import globus.shops.demo.app.domain.shop.detail.DetailShopProvider;
import globus.shops.demo.app.domain.shop.detail.ShopDetailEntity;
import globus.shops.demo.app.domain.shop.detail.Status;
import io.reactivex.Observable;

public class DetailShopProviderImpl implements DetailShopProvider {
    @Override
    public Observable<ShopDetailEntity> getDetailShopById(int id) {
        ShopDetailEntity entity = new ShopDetailEntity();
        entity.setId(id);
        entity.setTitle("Shop number " + id);
        entity.setAddress("Rostov, Budennovsky Prospekt " + id);
        entity.setNumber(id);
        entity.setOpenDate(1259654400000L);
        entity.setProfit(3000000.26);
        entity.setStatus(Status.OPEN_SOON);
        entity.setLatitude(47.221257);
        entity.setLongitude(39.706851);
        return Observable.just(entity);
    }
}
