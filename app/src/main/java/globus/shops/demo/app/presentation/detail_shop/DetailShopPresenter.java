package globus.shops.demo.app.presentation.detail_shop;

import javax.inject.Inject;

import globus.shops.demo.app.domain.shop.detail.GetDetailShopInteractor;
import globus.shops.demo.app.presentation.base.BasePresenter;

public class DetailShopPresenter extends BasePresenter<DetailShopView, DetailShopRouter> {

    private final GetDetailShopInteractor mGetDetailShopInteractor;

    @Inject
    public DetailShopPresenter(GetDetailShopInteractor getDetailShopInteractor) {
        mGetDetailShopInteractor = getDetailShopInteractor;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void loadDetailShop(int id) {
        getView().showLoading();
        mGetDetailShopInteractor.execute(id, result -> {
            getView().displayDetailShop(result);
        }, error -> {
            getView().showError(error.getMessage());
        }, () -> {
            getView().hideLoading();
        });
    }
}
