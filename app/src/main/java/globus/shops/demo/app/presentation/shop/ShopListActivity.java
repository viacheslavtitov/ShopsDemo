package globus.shops.demo.app.presentation.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.ShopEntity;
import globus.shops.demo.app.presentation.base.BaseActivity;
import globus.shops.demo.app.presentation.base.Layout;
import globus.shops.demo.app.presentation.detail_shop.DetailShopActivity;
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
    @BindView(R.id.container_filter)
    ViewGroup mContainerFilter;

    private ShopListActivityComponent mComponent;
    private ShopListAdapter mAdapter;

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
        mPresenter.loadShops(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btn_filter)
    public void onFilterClick(View view) {
        mContainerFilter.setVisibility(mContainerFilter.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @OnClick({R.id.radio_btn_address, R.id.radio_btn_number})
    public void onSortChooseClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_btn_address:
                if (checked)
                    mPresenter.sortByAddress(mAdapter.getData());
                break;
            case R.id.radio_btn_number:
                if (checked)
                    mPresenter.sortByNumber(mAdapter.getData());
                break;
        }
    }

    @Override
    public void openDetailShop(ShopEntity entity) {
        if (entity == null) return;
        Intent intent = new Intent(this, DetailShopActivity.class);
        intent.putExtra(DetailShopActivity.BUNDLE_SHOP_ID, entity.getId());
        startActivity(intent);
    }

    @Override
    public void displayShops(List<ShopEntity> shopList) {
        mAdapter = new ShopListAdapter(shopList, this);
        mRecyclerView.setAdapter(mAdapter);
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
