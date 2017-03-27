package com.eastarcreative.efendi.pawon_test.model;

/**
 * Created by gogo on 3/27/2017.
 */

public class Todo {
    int user_id, id;
    String title;
    boolean completed;

    public Todo(int user_id, int id, String title, int completed) {
        this.user_id = user_id;
        this.id = id;
        this.title = title;
        if(completed==1){
            this.completed=true;
        }else{
            this.completed=false;
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
