package com.barryirvine.mercari.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.barryirvine.mercari.R;
import com.barryirvine.mercari.api.ItemsService;
import com.barryirvine.mercari.model.FeedResponse;
import com.barryirvine.mercari.model.Item;
import com.barryirvine.mercari.ui.activity.MainActivity;
import com.barryirvine.mercari.ui.adapter.FeedAdapter;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FeedFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FeedAdapter mAdapter;
    private String mPathSegment;

    public FeedFragment() {
    }

    public static FeedFragment newInstance(@NonNull final String pathSegment) {
        final FeedFragment fragment = new FeedFragment();
        final Bundle args = new Bundle();
        args.putString(Args.FEED_TYPE_PATH_SEGMENT, pathSegment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPathSegment = getArguments().getString(Args.FEED_TYPE_PATH_SEGMENT);
        getResults();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (savedInstanceState == null) {
            mAdapter = new FeedAdapter(Collections.<Item>emptyList());
            mRecyclerView.setAdapter(mAdapter);
        }
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        getResults();
    }

    private void getResults() {
        ItemsService.get(getContext().getApplicationContext())
                .getItems(mPathSegment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<FeedResponse>() {
                            @Override
                            public void accept(final FeedResponse feed) throws Exception {
                                mSwipeRefreshLayout.setRefreshing(false);
                                mAdapter = new FeedAdapter(feed.getItems());
                                mRecyclerView.setAdapter(mAdapter);
                                Toast.makeText(getContext(), "Success" + feed.getItems().size(), Toast.LENGTH_LONG).show();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(final Throwable throwable) throws Exception {
                                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();

                            }
                        });
    }

    private static class Args {
        private static final String FEED_TYPE_PATH_SEGMENT = "FEED_TYPE_PATH_SEGMENT";
    }
}
