package com.sapstrt.emanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pteltu on 11/28/13.
 */
public class GroupSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="emanager.db";
    public static final Integer DATABASE_VERSION=1;

    public static final String TABLE_GROUP="group";
    public static final String TABLE_USER="user";
    public static final String COLUMN_ID="_id";
    public static final String USER_COLUMN_ID="_id";
    public static final String COLUMN_GROUP_ID="group_id";
    public static final String COLUMN_GROUP_NAME="group_name";
    public static final String COLUMN_USER_ID="user_id";


    public static final String CREATE_TABLE="create table "+ TABLE_GROUP +
            "("+ COLUMN_ID +" integer primary key autoincrement"+","
            +COLUMN_GROUP_ID+" integer,"+
            COLUMN_GROUP_NAME +" text,"+COLUMN_USER_ID+" integer"+
            " FOREIGN KEY ("+COLUMN_USER_ID+") REFERENCES "+TABLE_USER+" ("+USER_COLUMN_ID+"));";
    public GroupSQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ExpenseSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
        onCreate(db);
    }
}
