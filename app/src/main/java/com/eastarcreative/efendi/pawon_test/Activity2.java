package com.eastarcreative.efendi.pawon_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.eastarcreative.efendi.pawon_test.model.ListAdapter;
import com.eastarcreative.efendi.pawon_test.sqlite.DatabaseHandler;

public class Activity2 extends AppCompatActivity {
    DatabaseHandler db;
    private ListView lvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().setTitle("Pawon Android Dev Test");

        db = new DatabaseHandler(this);

        ListAdapter adapter = new ListAdapter(db.getAllTodo(), Activity2.this);
        lvItem = (ListView)findViewById(R.id.lv_item);
        lvItem.setAdapter(adapter);
    }
}
