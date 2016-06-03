package com.daffpunks.riodejaneiro2016.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daffpunks.riodejaneiro2016.Adapter.NewsListAdapter;
import com.daffpunks.riodejaneiro2016.Database.NewsCursorLoader;
import com.daffpunks.riodejaneiro2016.EndlessRecyclerViewScrollListener;
import com.daffpunks.riodejaneiro2016.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 25.04.2016.
 */
public class NewsFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener{

    private static final int TASK_LOADER_ID = 0;

    NewsCursorLoader newsCursorLoader;
    NewsListAdapter newsListAdapter;

    @Bind(R.id.toolbar)          Toolbar            mToolbar;
    @Bind(R.id.list)             RecyclerView       recyclerView;
    @Bind(R.id.swipe_container)  SwipeRefreshLayout swipeLayout;

    LinearLayoutManager linearLayoutManager;

    View root;

    private Context mContext;

    public NewsFragment(){
        //Empty constructor
    }

    public static NewsFragment newInstance(){
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.news_fragment,container,false);
        ButterKnife.bind(this, root);
        mContext = getActivity().getApplicationContext();
        mToolbar.setTitle(R.string.drawer_title_news);

        swipeLayout.setOnRefreshListener(this);



        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        setupToolbarForFragment(mToolbar);

        setupRecyclerView();

        getTaskLoader();

        getLoaderManager().restartLoader(TASK_LOADER_ID, null, this);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("Some","onLoadMore called");
            }
        });

        return root;
    }

    private void getTaskLoader() {
        newsCursorLoader = new NewsCursorLoader(mContext);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new NewsCursorLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        newsListAdapter.swapCursor(cursor);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        newsListAdapter.swapCursor(null);
    }

    private void setupRecyclerView() {
        newsListAdapter = new NewsListAdapter(getActivity(), null);

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setItemViewCacheSize(1);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(newsListAdapter);


    }

    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(true);

        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoaderManager().restartLoader(TASK_LOADER_ID, null, NewsFragment.this);
                recyclerView.getAdapter().notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 1500);


    }
}
