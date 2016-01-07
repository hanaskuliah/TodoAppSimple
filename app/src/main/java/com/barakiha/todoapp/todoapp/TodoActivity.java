package com.barakiha.todoapp.todoapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.barakiha.todoapp.todoapp.db.TaskContract;
import com.barakiha.todoapp.todoapp.db.TaskDBHelper;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab;
    ListView listTask;
    ListAdapter listAdapter;

    AddListFragment dialogFragment;
    FragmentManager fm;

    //for database
    private TaskDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        SQLiteDatabase sqlDB = new TaskDBHelper(this).getWritableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns.TASK},
                null,null,null,null,null);

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            Log.d("MainActivity cursor",
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    TaskContract.Columns.TASK)));
        }
        listTask=(ListView)findViewById(R.id.listView1);
        updateUI();




        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        dialogFragment=new AddListFragment();
        fm=getSupportFragmentManager();

    }

    protected void showDialog() {
        dialogFragment.show(fm,"dialog_add_list");
    }



    public void saveToDB(String param){
        Log.d("MainActivity", param);

        helper = new TaskDBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.clear();
        values.put(TaskContract.Columns.TASK,param);

        db.insertWithOnConflict(TaskContract.TABLE, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void updateUI() {
        helper = new TaskDBHelper(TodoActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null,null,null,null,null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[] { TaskContract.Columns.TASK},
                new int[] { R.id.taskTextView},
                0
        );
        listTask.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
              // Toast.makeText(this,"lalalala",Toast.LENGTH_SHORT).show();
                showDialog();
                break;

        }
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                TaskContract.TABLE,
                TaskContract.Columns.TASK,
                task);


        helper = new TaskDBHelper(TodoActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        updateUI();
    }
}
