package com.eastarcreative.efendi.pawon_test.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eastarcreative.efendi.pawon_test.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gogo on 3/27/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_list";

    private static final String TABLE_TODO = "todo";

    private static final String KEY_USERID = "userId";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_COMPLETED = "completed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_USERID        + " INTEGER,"
                + KEY_ID            + " INTEGER,"
                + KEY_TITLE         + " TEXT,"
                + KEY_COMPLETED     + " INTEGER" + ","
                + " PRIMARY KEY ("+KEY_USERID+","+KEY_ID+"))";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);

        // Create tables again
        onCreate(db);
    }

    public void addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, todo.getUser_id());
        values.put(KEY_ID, todo.getId());
        values.put(KEY_TITLE, todo.getTitle());

        int is_completed = 0;
        if(todo.isCompleted() == true){
            is_completed = 1;
        }
        values.put(KEY_COMPLETED, is_completed);

        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public ArrayList<Todo> getAllTodo() {
        ArrayList<Todo> todoList = new ArrayList<Todo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)));
                todoList.add(todo);
            } while (cursor.moveToNext());
        }
        return todoList;
    }



    public int getTodoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
