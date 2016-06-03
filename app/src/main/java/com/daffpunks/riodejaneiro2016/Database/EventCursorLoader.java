package com.daffpunks.riodejaneiro2016.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

/**
 * Created by User on 25.04.2016.
 */
public class EventCursorLoader extends CursorLoader {

    private DatabaseHandler handler;
    private SQLiteDatabase db;

    private int day;

    public EventCursorLoader(Context context, int Day) {
        super(context);
        this.day = Day;
    }

    @Override
    public Cursor loadInBackground() {
        handler = new DatabaseHandler(getContext());
        db = handler.getReadableDatabase();

        return db.query(DatabaseHandler.TABLE_EVENTS, null,
                DatabaseHandler.EVENT_DAY + " = ?",
                new String[] { String.valueOf(day) },
                null, null, null, null);
    }

    @Override
    protected void onStopLoading() {
        //db.close();
        //handler.close();
        super.onStopLoading();
    }
}
