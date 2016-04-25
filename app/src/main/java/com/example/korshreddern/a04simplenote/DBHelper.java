package com.example.korshreddern.a04simplenote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Korshreddern on 22-Apr-16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "NoteDB.sqlite";
    public static final int DB_VER = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    /**
     * TABLE notes
     *  id      int (PRIMARY KEY) (AUTOINCREASE)
     *  date    text
     *  message text
     * */
    public void onCreate(SQLiteDatabase database) {
        String sql;
        sql = "CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, message TEXT);";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldver, int newver) {
        database.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(database);
    }
}