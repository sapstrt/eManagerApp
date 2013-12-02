package com.sapstrt.emanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.User;
import com.sapstrt.emanager.exception.ExpenseNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/28/13.
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private UserSQLiteHelper dbHelper;
    private String[] allColumns={UserSQLiteHelper.COLUMN_ID,UserSQLiteHelper.COLUMN_EMAIL_ID};


    public UserDataSource(Context context) {
        dbHelper = new UserSQLiteHelper(context);

    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createUser(User user)
    {
        try {
            open();
            if(null!=user)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(UserSQLiteHelper.COLUMN_EMAIL_ID,user.getEmailId());

                database.insert(UserSQLiteHelper.TABLE_USER,null,contentValues);

            }
            else
                throw new RuntimeException("User Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }

    }
    public void editUser(User user)
    {

        try {
            open();
            if(null!=user)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(UserSQLiteHelper.COLUMN_EMAIL_ID,user.getEmailId());

                long updateId=database.update(UserSQLiteHelper.TABLE_USER,contentValues,UserSQLiteHelper.COLUMN_ID +"="+user.getUserId().toString(),null);

            }
            else
                throw new RuntimeException("User Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }


    }


    public void deleteUser(Integer userId) {
        try {
            open();
            database.delete(UserSQLiteHelper.TABLE_USER,UserSQLiteHelper.COLUMN_ID + "="+userId,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }

    }

    public List<User> getAllUsers()
    {
        List<User> userList=new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + UserSQLiteHelper.TABLE_USER;
        try {
            open();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setUserId(cursor.getInt(0));
                    user.setEmailId(cursor.getString(1));

                    // Adding contact to list
                    userList.add(user);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return userList;
    }


}
