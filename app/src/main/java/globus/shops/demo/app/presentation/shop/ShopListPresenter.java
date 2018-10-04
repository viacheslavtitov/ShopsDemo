package globus.shops.demo.app.presentation.shop;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import globus.shops.demo.app.domain.shop.GetShopListInteractor;
import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.presentation.base.BasePresenter;
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
