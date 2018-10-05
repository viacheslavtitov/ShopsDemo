package globus.shops.demo.app.presentation.detail_shop;

import globus.shops.demo.app.domain.shop.detail.ShopDetailEntity;
import globus.shops.demo.app.presentation.base.BaseView;

public interface DetailShopView extends BaseView {

    void displayDetailShop(ShopDetailEntity entity);

}
