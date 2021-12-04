package com.example.dream_routine;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "db_Dream_Routine";
    // table name
    private static final String TABLE_USER = "tbl_user";
    private static final String TABLE_TASK = "tbl_task";
    // mutual column names
    private static final String KEY_ID = "_id";
    // user table
    private static final String KEY_USER_NAME = "User_name";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PASS = "Password";
    private static final String KEY_EMAIL = "Email";
    // task table
    private static final String KEY_TASK_NAME = "Task_name";
    private static final String KEY_TASK_TAG = "Task_tag";
    private static final String KEY_DEADLINE = "Deadline";
    private static final String KEY_TASK_NOTE = "Task_note";
    private static final String KEY_USER_ID = "User_id";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAME + " TEXT,"
            + KEY_NAME + " TEXT," + KEY_PASS + " VARCHAR(12)," + KEY_EMAIL + " TEXT )";

    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK_NAME + " TEXT,"
            + KEY_TASK_TAG + " TEXT," + KEY_DEADLINE + " DATE," + KEY_TASK_NOTE + " TEXT," + KEY_USER_ID + " INTERGER )";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public void insertUser(User user) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASS, user.getUserPass());
        values.put(KEY_EMAIL, user.getUserEmail());

        // them vao CSDL
        database.insert(TABLE_USER, null, values);
    }

    public void insertTask(Task task) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_NAME, task.getTaskName());
        values.put(KEY_TASK_TAG, task.getTaskTag());
        values.put(KEY_DEADLINE, task.getTaskDeadline());
        values.put(KEY_TASK_NOTE, task.getTaskNote());
        values.put(KEY_USER_ID, task.getUserId());

        // them vao CSDL
        database.insert(TABLE_TASK, null, values);
    }

    @SuppressLint("Range")
    public User getUser(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();

        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_ID + " = " + id;

        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);

        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);

        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }

        // dong goi thong tin vao 1 doi tuong user
        User user = new User();
        user.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        user.setUserName(c.getString(c.getColumnIndex(KEY_USER_NAME)));
        user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        user.setUserPass(c.getString(c.getColumnIndex(KEY_PASS)));
        user.setUserEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));

        // tra ve 1 user
        return user;
    }

    @SuppressLint("Range")
    public Task getTask(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();

        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_TASK + " WHERE " + KEY_ID + " = " + id;

        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);

        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);

        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }

        // dong goi thong tin vao 1 doi tuong task
        Task task = new Task();
        task.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        task.setTaskName(c.getString(c.getColumnIndex(KEY_TASK_NAME)));
        task.setTaskTag(c.getString(c.getColumnIndex(KEY_TASK_TAG)));
        task.setTaskDeadline(c.getString(c.getColumnIndex(KEY_DEADLINE)));
        task.setTaskNote(c.getString(c.getColumnIndex(KEY_TASK_NOTE)));
        task.setUserId(c.getString(c.getColumnIndex(KEY_USER_ID)));

        // tra ve 1 task
        return task;
    }

    @SuppressLint("Range")
    public ArrayList<Task> getAllTask() {
        ArrayList<Task> arrTask = new ArrayList<Task>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_TASK;

        LogUtil.LogD(LOG, selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);

        if (c != null) {
            c.moveToFirst();

            do {
                // dong goi thong tin vao 1 doi tuong task
                Task task = new Task();

                task.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
                task.setTaskName(c.getString(c.getColumnIndex(KEY_TASK_NAME)));
                task.setTaskTag(c.getString(c.getColumnIndex(KEY_TASK_TAG)));
                task.setTaskDeadline(c.getString(c.getColumnIndex(KEY_DEADLINE)));
                task.setTaskNote(c.getString(c.getColumnIndex(KEY_TASK_NOTE)));
                task.setUserId(c.getString(c.getColumnIndex(KEY_USER_ID)));

                arrTask.add(task);
            } while (c.moveToNext()); // chuyen toi dong tiep theo
        }

        // tra ve danh sach cac task
        return arrTask;
    }

    public void updateTask(Task task, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_NAME, task.getTaskName());
        values.put(KEY_TASK_TAG, task.getTaskTag());
        values.put(KEY_DEADLINE, task.getTaskDeadline());
        values.put(KEY_TASK_NOTE, task.getTaskNote());
        values.put(KEY_USER_ID, task.getUserId());

        // sua task co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_TASK, values, KEY_ID + " = " + _id, null);
    }

    // xoa task co ID = _id
    public void deleteStudent(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_TASK, KEY_ID + " = " + _id, null);
    }

    public boolean CheckValid (String username, String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from tbl_user where User_name = \"" + username + "\" and Password = \""+ password +"\"", null);
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
