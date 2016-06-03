package com.daffpunks.riodejaneiro2016.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daffpunks.riodejaneiro2016.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 26.04.2016.
 */
public class MedalsListAdapter extends CursorRecyclerAdapter<RecyclerView.ViewHolder> {

    private final Context mContext;  // Current context

    int    cId;
    String cTitle;
    int    cGold;
    int    cSilver;
    int    cBronze;
    int    cTotal;
    int    cFlag;


    public MedalsListAdapter(Context context, Cursor cursor) {
        super(cursor);
        this.mContext = context;
    }

    @Override
    public MedalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medal, parent, false);
        return new MedalHolder(view);
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        MedalHolder holder = (MedalHolder) viewHolder;
        handleRemind(holder, cursor);
    }

    private void handleRemind(final MedalHolder holder, final Cursor cursor) {
        cId      = cursor.getInt(0);
        cTitle   = cursor.getString(1);
        cGold    = cursor.getInt(2);
        cSilver  = cursor.getInt(3);
        cBronze  = cursor.getInt(4);
        cTotal   = cursor.getInt(5);
        cFlag    = cursor.getInt(6);


        holder.idfield.setText(cId + "");
        holder.name.setText(cTitle);
        holder.gold.setText(cGold + "");
        holder.silver.setText(cSilver + "");
        holder.bronze.setText(cBronze + "");
        holder.total.setText(cTotal + "");
        holder.flag.setBackgroundResource(cFlag);

        if(cId % 2 == 0 )
            holder.relativeLayout.setBackgroundColor(0xFFFFFFFF);
        else
            holder.relativeLayout.setBackgroundColor(0xFFBBDEFB);

    }

    static class MedalHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.backLayout)   RelativeLayout   relativeLayout;
        @Bind(R.id.idfield)      TextView         idfield;
        @Bind(R.id.flag)         ImageView        flag;
        @Bind(R.id.name)         TextView         name;
        @Bind(R.id.gold)         TextView         gold;
        @Bind(R.id.silver)       TextView         silver;
        @Bind(R.id.bronze)       TextView         bronze;
        @Bind(R.id.total)        TextView         total;
        public MedalHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
