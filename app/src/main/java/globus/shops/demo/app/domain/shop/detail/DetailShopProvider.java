package globus.shops.demo.app.domain.shop.detail;

import io.reactivex.Observable;

public interface DetailShopProvider {

    Observable<ShopDetailEntity> getDetailShopById(int id);

}
