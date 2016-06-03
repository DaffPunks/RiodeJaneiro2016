package com.daffpunks.riodejaneiro2016.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

/**
 * Created by User on 26.04.2016.
 */
public class NewsCursorLoader extends CursorLoader {

    private DatabaseHandler handler;
    private SQLiteDatabase db;

    public NewsCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        handler = new DatabaseHandler(getContext());
        db = handler.getReadableDatabase();
        return db.query(DatabaseHandler.TABLE_NEWS, null, null, null, null, null, null, null);
    }

    @Override
    protected void onStopLoading() {
        //db.close();
        //handler.close();
        super.onStopLoading();
    }
}
