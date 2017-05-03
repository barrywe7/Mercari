package com.barryirvine.mercari.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.barryirvine.mercari.BR;
import com.barryirvine.mercari.R;
import com.barryirvine.mercari.model.Item;
import com.barryirvine.mercari.viewmodel.ItemViewModel;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final List<Item> mItems;

    public FeedAdapter(final List<Item> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, @LayoutRes final int viewType) {
        return new FeedAdapter.ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @LayoutRes
    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_feed;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(new ItemViewModel(holder.itemView.getContext(), mItems.get(position)));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding mBinding;

        ViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(@NonNull final ItemViewModel itemViewModel) {
            mBinding.setVariable(BR.viewModel, itemViewModel);
            mBinding.executePendingBindings();
        }
    }
}
