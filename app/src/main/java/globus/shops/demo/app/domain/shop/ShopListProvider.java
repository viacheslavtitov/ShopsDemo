package globus.shops.demo.app.domain.shop;

import java.util.List;

import io.reactivex.Observable;

public interface ShopListProvider {

    Observable<List<ShopEntity>> getAllShops();

}
