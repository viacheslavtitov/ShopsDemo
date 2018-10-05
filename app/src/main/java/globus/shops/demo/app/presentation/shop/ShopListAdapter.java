package globus.shops.demo.app.presentation.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import globus.shops.demo.app.R;
import globus.shops.demo.app.domain.shop.ShopEntity;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopItemHolder> {

    private List<ShopEntity> mData;
    private ShopListRouter mRouter;

    public ShopListAdapter(List<ShopEntity> data, ShopListRouter router) {
        mData = data;
        mRouter = router;
    }

    @NonNull
    @Override
    public ShopItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_shop_list_item, parent, false);
        ShopItemHolder vh = new ShopItemHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemHolder holder, int position) {
        ShopEntity entity = getItem(position);
        if (entity != null) {
            holder.textViewTitle.setText(entity.getTitle());
            holder.textViewAddress.setText(entity.getAddress());
            holder.textViewNumber.setText(String.valueOf(entity.getId()));
        }
        holder.itemView.setOnClickListener(click -> {
            if (mRouter != null) {
                mRouter.openDetailShop(entity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public ShopEntity getItem(int position) {
        return mData != null && mData.size() > position ? mData.get(position) : null;
    }

    public List<ShopEntity> getData() {
        return mData;
    }

    static class ShopItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title)
        TextView textViewTitle;
        @BindView(R.id.text_view_address)
        TextView textViewAddress;
        @BindView(R.id.text_view_number)
        TextView textViewNumber;

        public ShopItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
