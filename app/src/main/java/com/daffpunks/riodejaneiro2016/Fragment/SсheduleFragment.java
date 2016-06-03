package com.daffpunks.riodejaneiro2016.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daffpunks.riodejaneiro2016.Adapter.EventListAdapter;
import com.daffpunks.riodejaneiro2016.Database.EventCursorLoader;
import com.daffpunks.riodejaneiro2016.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 25.04.2016.
 */
public class SсheduleFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int TASK_LOADER_ID = 0;

    EventCursorLoader   eventCursorLoader;
    EventListAdapter    eventListAdapter;

    @Bind(R.id.listsh)    RecyclerView    recyclerView;

    private Context mContext;

    int currentDay;

    public SсheduleFragment(){
        //Empty constructor
    }

    public static SсheduleFragment newInstance(int Day) {
        SсheduleFragment fragment = new SсheduleFragment();
        Bundle args = new Bundle();
        args.putInt("day",Day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDay = getArguments().getInt("day");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);
        ButterKnife.bind(this, root);
        mContext = getActivity().getApplicationContext();

        setupRecyclerView();

        getTaskLoader();

        getLoaderManager().initLoader(TASK_LOADER_ID, null, this);

        return root;
    }
    private void getTaskLoader() {
        eventCursorLoader = new EventCursorLoader(mContext,currentDay);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return eventCursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        eventListAdapter.swapCursor(cursor);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        eventListAdapter.swapCursor(null);
    }

    private void setupRecyclerView() {
        eventListAdapter = new EventListAdapter(getActivity(), null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(eventListAdapter);

    }
}
