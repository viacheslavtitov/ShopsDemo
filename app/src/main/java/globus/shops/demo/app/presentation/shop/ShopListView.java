package globus.shops.demo.app.presentation.shop;

import java.util.List;

import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.presentation.base.BaseView;

public interface ShopListView extends BaseView {

    void displayShops(List<ShopEntity> shopList);

}
