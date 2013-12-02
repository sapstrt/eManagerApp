package com.sapstrt.emanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pteltu on 11/28/13.
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="emanager.db";
    public static final Integer DATABASE_VERSION=1;

    public static final String TABLE_USER="user";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_EMAIL_ID="email_id";


    public static final String CREATE_TABLE="create table "+ TABLE_USER +
            "("+ COLUMN_ID +" integer primary key autoincrement"+","
            + COLUMN_EMAIL_ID +" text);";
    public UserSQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ExpenseSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
