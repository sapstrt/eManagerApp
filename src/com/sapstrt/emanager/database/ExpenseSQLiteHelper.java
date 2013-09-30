package com.sapstrt.emanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cambas on 9/25/13.
 */
public class ExpenseSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="emanager";
    public static final Integer DATABASE_VERSION=1;

    public static final String TABLE_EXPENSES="expense";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="expense_name";
    public static final String COLUMN_AMOUNT="amount";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_LOCATION="location";
    public static final String COLUMN_MODE="mode";

    public static final String CREATE_TABLE="create table "+ TABLE_EXPENSES +
            "("+ COLUMN_ID +" integer primary key autoincrement"+","
            + COLUMN_NAME +" text,"+COLUMN_AMOUNT+" real,"+COLUMN_DATE + " text," +
            COLUMN_LOCATION +" text," + COLUMN_MODE + " text);";
    public ExpenseSQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ExpenseSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }
}
