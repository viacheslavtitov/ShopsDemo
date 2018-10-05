package globus.shops.demo.app.presentation.detail_shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.detail.ShopDetailEntity;
import globus.shops.demo.app.presentation.base.BaseActivity;
import globus.shops.demo.app.presentation.base.Layout;
import globus.shops.demo.app.presentation.injection.DataModule;
import globus.shops.demo.app.presentation.injection.DomainModule;
import globus.shops.demo.app.presentation.injection.detail_shop.DaggerDetailShopActivityComponent;
import globus.shops.demo.app.presentation.injection.detail_shop.DetailShopActivityComponent;
import globus.shops.demo.app.presentation.injection.detail_shop.DetailShopModule;

@Layout(id = R.layout.activity_detail_shop)
public class DetailShopActivity extends BaseActivity implements DetailShopView, DetailShopRouter {

    public static final String BUNDLE_SHOP_ID = "globus.shops.demo.app.presentation.detail_shop.DetailShopActivity.BUNDLE_SHOP_ID";

    @Inject
    DetailShopPresenter mPresenter;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.text_view_title)
    TextView mTextViewTitle;
    @BindView(R.id.text_view_number)
    TextView mTextViewNumber;
    @BindView(R.id.text_view_address)
    TextView mTextViewAddress;
    @BindView(R.id.text_view_profit)
    TextView mTextViewProfit;
    @BindView(R.id.text_view_open_date)
    TextView mTextViewOpenDate;
    @BindView(R.id.text_view_open_status)
    TextView mTextViewStatus;

    private DetailShopActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerDetailShopActivityComponent.builder()
                .dataModule(new DataModule())
                .detailShopModule(new DetailShopModule())
                .domainModule(new DomainModule())
                .build();
        mComponent.inject(this);

        mPresenter.setRouter(this);
        mPresenter.setView(this);
        if (getIntent() != null && getIntent().hasExtra(BUNDLE_SHOP_ID)) {
            mPresenter.loadDetailShop(this, getIntent().getIntExtra(BUNDLE_SHOP_ID, -1));
        } else {
            throw new RuntimeException("Shop ID can't be empty");
        }
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

    @Override
    public void openMap(double latitude, double longitude) {

    }

    @Override
    public void displayDetailShop(ShopDetailEntity entity) {
        if (entity == null) return;
        mTextViewTitle.setText(entity.getTitle());
        mTextViewAddress.setText(entity.getAddress());
        mTextViewNumber.setText(String.valueOf(entity.getNumber()));

        final String currencySymbol = getString(R.string.currency_symbol);
        final String profit = String.format("%.2f %s", entity.getProfit(), currencySymbol);
        mTextViewProfit.setText(profit);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        mTextViewOpenDate.setText(dateFormat.format(new Date(entity.getOpenDate())));

        switch (entity.getStatus()) {
            case OPEN_SOON:
                mTextViewStatus.setText(R.string.open);
                break;
            case OPENING_CLOSED:
                mTextViewStatus.setText(R.string.closed);
                break;
            case REPAIR_CLOSED:
                mTextViewStatus.setText(R.string.repair);
                break;
        }
    }
}
