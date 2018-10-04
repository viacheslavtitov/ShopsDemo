package globus.shops.demo.app.presentation.shop;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.presentation.base.BaseActivity;
import globus.shops.demo.app.presentation.base.Layout;
import globus.shops.demo.app.presentation.injection.DataModule;
import globus.shops.demo.app.presentation.injection.DomainModule;
import globus.shops.demo.app.presentation.injection.shop.DaggerShopListActivityComponent;
import globus.shops.demo.app.presentation.injection.shop.ShopListActivityComponent;
import globus.shops.demo.app.presentation.injection.shop.ShopListModule;

@Layout(id = R.layout.activity_shop_list)
public class ShopListActivity extends BaseActivity implements ShopListRouter, ShopListView {

    @Inject
    ShopListPresenter mPresenter;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ShopListActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerShopListActivityComponent.builder()
                .dataModule(new DataModule())
                .domainModule(new DomainModule())
                .shopListModule(new ShopListModule())
                .build();
        mComponent.inject(this);

        mPresenter.setRouter(this);
        mPresenter.setView(this);
        mPresenter.loadShops();
    }

    @Override
    public void openDetailShop(ShopEntity entity) {

    }

    @Override
    public void displayShops(List<ShopEntity> shopList) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
