package globus.shops.demo.app.presentation.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.GetShopListInteractor;
import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.presentation.base.BasePresenter;
import globus.shops.demo.app.utils.DeviceUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    @SuppressLint("CheckResult")
    public void loadShops(FragmentActivity activity) {
        if (DeviceUtils.isOnline(activity)) {
            new RxPermissions(activity)
                    .request(Manifest.permission.ACCESS_NETWORK_STATE)
                    .subscribe(granted -> {
                        if (granted) {
                            getView().showLoading();
                            mGetShopListInteractor.execute(result -> {
                                getView().displayShops(result);
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

    @SuppressLint("CheckResult")
    public void sortByAddress(List<ShopEntity> shopList) {
        if (shopList == null || shopList.isEmpty()) return;
        Observable.just(shopList)
                .map(sortedList -> {
                    Collections.sort(sortedList, new AddressComparator());
                    return sortedList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> getView().displayShops(result),
                        error -> getView().showError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void sortByNumber(List<ShopEntity> shopList) {
        if (shopList == null || shopList.isEmpty()) return;
        Observable.just(shopList)
                .map(sortedList -> {
                    Collections.sort(sortedList, new NumberComparator());
                    return sortedList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> getView().displayShops(result),
                        error -> getView().showError(error.getMessage())
                );
    }

    private class AddressComparator implements Comparator<ShopEntity> {

        public int compare(ShopEntity entity1, ShopEntity entity2) {
            return entity1.getAddress().compareTo(entity2.getAddress());
        }
    }

    private class NumberComparator implements Comparator<ShopEntity> {

        public int compare(ShopEntity entity1, ShopEntity entity2) {
            return entity1.getId() - entity2.getId();
        }
    }
}
