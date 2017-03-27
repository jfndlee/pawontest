package com.eastarcreative.efendi.pawon_test.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastarcreative.efendi.pawon_test.R;

import java.util.ArrayList;

/**
 * Created by gogo on 3/27/2017.
 */

public class ListAdapter extends BaseAdapter {

    ArrayList<Todo> listItem;
    Activity activity;

    public ListAdapter(ArrayList<Todo> listItem, Activity activity) {
        this.listItem = listItem;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.todo_list, null);
            holder.todo_title = (TextView)view.findViewById(R.id.todo_title);
            holder.todo_checkbox = (CheckBox) view.findViewById(R.id.todo_checkbox);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        Todo todo = (Todo) getItem(position);
        holder.todo_title.setText(todo.getTitle());

        if(todo.isCompleted()==true){
            holder.todo_checkbox.setChecked(true);
        }else{
            holder.todo_checkbox.setChecked(false);
        }

        return view;
    }

    static class ViewHolder{
        CheckBox todo_checkbox;
        TextView todo_title;
    }
}
