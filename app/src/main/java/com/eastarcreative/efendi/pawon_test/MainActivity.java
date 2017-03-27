package com.eastarcreative.efendi.pawon_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eastarcreative.efendi.pawon_test.model.Todo;
import com.eastarcreative.efendi.pawon_test.sqlite.DatabaseHandler;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";
    String api_url = "http://jsonplaceholder.typicode.com/todos";
    DatabaseHandler db;
    Button btn_todolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Pawon Android Dev Test");

        db = new DatabaseHandler(this);
        if(db.getTodoCount()==0){
            getData();
        }

        btn_todolist = (Button) findViewById(R.id.btn_todolist);
        btn_todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
            }
        });
    }

    private void getData() {
        Fuel.get(api_url).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void success(Request request, Response response, String data) {
                //do something when it is successful
                Toast.makeText(MainActivity.this, "success fetch data", Toast.LENGTH_SHORT).show();
                Log.d(TAG + "POST_RESULT", data);
                post_getData(data);
            }
        });
    }

    private void post_getData(String data) {
        int user_id, id;
        String title;
        Boolean completed;
        int is_completed;
        JSONArray response = null;
        try {
            response = new JSONArray(data);
            for (int i = 0; i < response.length(); i++) {
                user_id = response.getJSONObject(i).getInt("userId");
                id = response.getJSONObject(i).getInt("id");
                title = response.getJSONObject(i).getString("title");
                completed = response.getJSONObject(i).getBoolean("completed");
                if(completed==true){
                    is_completed=1;
                }else{
                    is_completed=0;
                }
                db.addTodo(new Todo(user_id,id,title,is_completed));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
