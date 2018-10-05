package globus.shops.demo.app.presentation.detail_shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.detail.GetDetailShopInteractor;
import globus.shops.demo.app.presentation.base.BasePresenter;
import globus.shops.demo.app.utils.DeviceUtils;

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

    @SuppressLint("CheckResult")
    public void loadDetailShop(FragmentActivity activity, int id) {
        if (DeviceUtils.isOnline(activity)) {
            new RxPermissions(activity)
                    .request(Manifest.permission.ACCESS_NETWORK_STATE)
                    .subscribe(granted -> {
                        if (granted) {
                            getView().showLoading();
                            mGetDetailShopInteractor.execute(id, result -> {
                                getView().displayDetailShop(result);
                            }, error -> {
                                getView().showError(error.getMessage());
                            }, () -> {
                                getView().hideLoading();
                            });
                        }
                    }, error -> {
                        getView().showError(error.getMessage());
                    });
        } else {
            getView().showError(activity.getString(R.string.error_no_internet_connection));
        }
    }
}
