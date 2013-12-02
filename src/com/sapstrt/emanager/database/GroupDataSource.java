package com.sapstrt.emanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapstrt.emanager.domain.Expense;
import com.sapstrt.emanager.domain.Group;
import com.sapstrt.emanager.exception.ExpenseNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/28/13.
 */
public class GroupDataSource {
    private SQLiteDatabase database;
    private GroupSQLiteHelper dbHelper;
    private String[] allColumns={GroupSQLiteHelper.COLUMN_ID,GroupSQLiteHelper.COLUMN_GROUP_ID,GroupSQLiteHelper.COLUMN_GROUP_NAME,
            GroupSQLiteHelper.COLUMN_USER_ID};


    public GroupDataSource(Context context) {
        dbHelper = new GroupSQLiteHelper(context);

    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createGroup(Group grp)
    {
        try {
            open();
            if(null!=grp)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(GroupSQLiteHelper.COLUMN_GROUP_ID,grp.getGrpId());
                contentValues.put(GroupSQLiteHelper.COLUMN_GROUP_NAME,grp.getGroupName());
                contentValues.put(GroupSQLiteHelper.COLUMN_USER_ID,grp.getUserId());

                database.insert(GroupSQLiteHelper.TABLE_GROUP,null,contentValues);

            }
            else
                throw new RuntimeException("Group Not Found");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        finally {
            close();
        }

    }
    public void editGroup(Group grp)
    {

        try {
            open();
            if(null!=grp)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(GroupSQLiteHelper.COLUMN_GROUP_ID,grp.getGrpId());
                contentValues.put(GroupSQLiteHelper.COLUMN_GROUP_NAME,grp.getGroupName());
                contentValues.put(GroupSQLiteHelper.COLUMN_USER_ID,grp.getUserId());

                long updateId=database.update(GroupSQLiteHelper.TABLE_GROUP,contentValues,GroupSQLiteHelper.COLUMN_ID +"="+grp.getUserId().toString(),null);

            }
            else
                throw new RuntimeException("Group Not Found");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        finally {
            close();
        }


    }


    public void deleteGroup(Integer grpID) {
        try {
            open();
            database.delete(GroupSQLiteHelper.TABLE_GROUP,GroupSQLiteHelper.COLUMN_ID + "="+grpID,null);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        finally {
            close();
        }

    }

    public List<Group> getAllGrps()
    {
        List<Group> grpList=new ArrayList<Group>();
        String selectQuery = "SELECT  * FROM " + ExpenseSQLiteHelper.TABLE_EXPENSES;
        try {
            open();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Group grp = new Group();
                    grp.setId(cursor.getInt(0));
                    grp.setGrpId(cursor.getInt(1));
                    grp.setGroupName(cursor.getString(2));
                    grp.setUserId(cursor.getInt(3));
                    // Adding contact to list
                    grpList.add(grp);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }finally {
            close();
        }
        return grpList;
    }

}
