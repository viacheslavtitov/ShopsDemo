package globus.shops.demo.app.presentation.shop;

import javax.inject.Inject;

import globus.shops.demo.app.domain.shop.GetShopListInteractor;
import globus.shops.demo.app.presentation.base.BasePresenter;

public class ShopListPresenter extends BasePresenter<ShopListView, ShopListRouter> {
    private final GetShopListInteractor mGetShopListInteractor;

    @Inject
    public ShopListPresenter(GetShopListInteractor getShopListInteractor) {
        mGetShopListInteractor = getShopListInteractor;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void loadShops() {
        getView().showLoading();
        mGetShopListInteractor.execute(result -> {
            getView().displayShops(result);
        }, error -> {
            getView().showError(error.getMessage());
        }, () -> {
            getView().hideLoading();
        });
    }
}
