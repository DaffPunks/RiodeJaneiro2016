package com.daffpunks.riodejaneiro2016.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daffpunks.riodejaneiro2016.Activity.NewsActivity;
import com.daffpunks.riodejaneiro2016.Database.DatabaseHandler;
import com.daffpunks.riodejaneiro2016.MainActivity;
import com.daffpunks.riodejaneiro2016.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 26.04.2016.
 */
public class NewsListAdapter extends CursorRecyclerAdapter<RecyclerView.ViewHolder> {

    private final Context mContext;  // Current context

    String cTitle;
    String cCategory;
    int    cPic;

    public NewsListAdapter(Context context, Cursor cursor) {
        super(cursor);
        this.mContext = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        NewsHolder holder = (NewsHolder) viewHolder;
        handleRemind(holder, cursor);
    }

    private void handleRemind(final NewsHolder holder, final Cursor cursor) {
        cTitle     = cursor.getString(1);
        cCategory  = cursor.getString(4);
        cPic       = cursor.getInt(5);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            holder.card.setRadius(0);
        }


        holder.title.setText(cTitle);
        holder.category.setText(cCategory);
        holder.image.setImageResource(cPic);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(mContext, NewsActivity.class);
                    mContext.startActivity(intent);
                }
        });

    }

    static class NewsHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card)         CardView        card;
        @Bind(R.id.image)        ImageView       image;
        @Bind(R.id.titleNews)    TextView        title;
        @Bind(R.id.categoryNews) TextView        category;
        @Bind(R.id.root)         LinearLayout    root;
        public NewsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
