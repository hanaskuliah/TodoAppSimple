package com.barakiha.todoapp.todoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Hanas Subakti on 1/7/2016.
 */
public class TaskDBHelper extends SQLiteOpenHelper {

    //harus memanggil super
    public TaskDBHelper(Context context) {
        super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        /*
        CREATE TABLE tasks (
    id int primary key auto_increment,
    task text) auto_increment = 1;
         */

        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT)", TaskContract.TABLE,
                        TaskContract.Columns.TASK);

        Log.d("TaskDBHelper", "Query to form table: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract.TABLE);
        onCreate(db);
    }
}
