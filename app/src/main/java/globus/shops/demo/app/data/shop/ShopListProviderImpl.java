package globus.shops.demo.app.data.shop;

import java.util.ArrayList;
import java.util.List;

import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.domain.shop.ShopListProvider;
import io.reactivex.Observable;

public class ShopListProviderImpl implements ShopListProvider {

    @Override
    public Observable<List<ShopEntity>> getAllShops() {
        List<ShopEntity> shopList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ShopEntity entity = new ShopEntity();
            entity.setId(i);
            entity.setTitle("Shop number " + i);
            entity.setAddress("Rostov, Budennovsky Prospekt " + i);
            shopList.add(entity);
        }
        return Observable.just(shopList);
    }
}
