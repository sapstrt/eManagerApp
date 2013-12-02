package com.sapstrt.emanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pteltu on 11/28/13.
 */
public class CategorySQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="emanager.db";
    public static final Integer DATABASE_VERSION=1;

    public static final String TABLE_CATEGORY="category";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_CATEGORY_NAME="category_name";


    public static final String CREATE_TABLE="create table "+ TABLE_CATEGORY +
            "("+ COLUMN_ID +" integer primary key autoincrement"+","
            + COLUMN_CATEGORY_NAME + " text);";

    public CategorySQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ExpenseSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);
    }
}
