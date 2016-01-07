package com.barakiha.todoapp.todoapp.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Hanas Subakti on 1/7/2016.
 */
public class TaskDBExecute {
    Activity activity;

    public TaskDBExecute(Activity activity){
        this.activity=activity;
    }

    public void saveToDB(String param){

        Log.d("MainActivity", param);

        TaskDBHelper helper = new TaskDBHelper(activity);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.clear();
        values.put(TaskContract.Columns.TASK,param);

        db.insertWithOnConflict(TaskContract.TABLE,null,values,
                SQLiteDatabase.CONFLICT_IGNORE);
    }

}
