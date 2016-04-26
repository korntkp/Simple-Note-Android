package com.example.korshreddern.a04simplenote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Korshreddern on 22-Apr-16.
 */
public class Note {
    public int id;
    public String date;
    public String message;

    public static ArrayList<Note> searchNote(Context context){
        ArrayList<Note> list = new ArrayList<Note>();
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT id, date, message FROM notes ORDER BY id DESC";
        Cursor sqlList = db.rawQuery(sql, null);
        if (! sqlList.moveToFirst()) {
            return list;
        }
        do {
            Note note = new Note();
            note.id = sqlList.getInt(0);
            note.date = sqlList.getString(1);
            note.message = sqlList.getString(2);
            list.add(note);
        }
        while (sqlList.moveToNext());

        sqlList.close();
        db.close();
        return list;
    }

    public void addNote(Context context, String mDate, String msg) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="INSERT INTO notes (date,message) VALUES(?,?)";
        db.execSQL(sql, new String []{
                mDate,
                msg
        });
        db.close();
    }

    /*
    * UPDATE table_name
    * SET column1 = value1, column2 = value2...., columnN = valueN
    * WHERE [condition];
    * */
    public void editNote(Context context, int id, String mDate, String msg){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="UPDATE notes SET date=?, message=? WHERE id=?;";
        db.execSQL(sql, new String []{
                mDate,
                msg,
                id+""
        });
        db.close();
    }

    public void deleteNote(Context context, int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM notes Where id=?";
        db.execSQL(sql, new String[]{
                id+""
        });
    }

    public void deleteNoteAll(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notes", null, null);
    }
}
