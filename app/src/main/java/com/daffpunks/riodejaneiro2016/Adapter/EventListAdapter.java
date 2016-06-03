package com.daffpunks.riodejaneiro2016.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daffpunks.riodejaneiro2016.R;
import com.daffpunks.riodejaneiro2016.Utils.SportUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 25.04.2016.
 */
public class EventListAdapter extends CursorRecyclerAdapter<RecyclerView.ViewHolder> {

    private final Context mContext;  // Current context

    String cTitle;
    String cTime;
    int    cSport;

    public EventListAdapter(Context context, Cursor cursor) {
        super(cursor);
        this.mContext = context;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        EventHolder holder = (EventHolder) viewHolder;
        handleRemind(holder, cursor);
    }

    private void handleRemind(final EventHolder holder, final Cursor cursor) {
        cTitle = cursor.getString(1);
        cTime  = cursor.getString(3);
        cSport = cursor.getInt(4);

        holder.title.setText(cTitle);
        holder.time.setText(cTime);
        holder.sporticon.setImageResource(SportUtils.getSport(cSport));

    }

    static class EventHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)     TextView   title;
        @Bind(R.id.time)      TextView   time;
        @Bind(R.id.sportIcon) ImageView  sporticon;
        public EventHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
