package com.example.dream_routine;

import java.util.ArrayList;
import java.util.Date;

public class Todo {
    String task;


    public Todo(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public static ArrayList<Task> initFTodo(DataHelper db,String date,int u_id){

        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList = db.getAllTodoTask(date,u_id);
        return arrayList;
    }


    public static ArrayList<String> initAll(DataHelper db){

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList = db.getAllTaskName();
        return arrayList;
    }
    public static ArrayList<Task> initByTag(DataHelper db,String date,String tag,int u_id){

        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList = db.getTaskTodoByTag(date,tag,u_id);
        return arrayList;
    }
    public static ArrayList<Task> initDayByTag(DataHelper db,String date,String tag,int u_id){

        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList = db.getDayTaskByTag(date,tag,u_id);
        return arrayList;
    }

    public static ArrayList<Task> initAllTrash(DataHelper db,int u_id){
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList = db.getAllTrash(u_id);
        return arrayList;
    }
}
