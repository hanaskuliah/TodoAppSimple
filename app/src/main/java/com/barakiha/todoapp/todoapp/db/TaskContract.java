package com.barakiha.todoapp.todoapp.db;

import android.provider.BaseColumns;

/**
 * Created by Hanas Subakti on 1/7/2016.
 */
public class TaskContract {

    public static final String DB_NAME = "com.barakiha.todoapp.todoapp.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TASK = "task";
        public static final String _ID = BaseColumns._ID;
    }
}
