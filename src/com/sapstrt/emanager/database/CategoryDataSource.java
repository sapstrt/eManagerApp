package com.sapstrt.emanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapstrt.emanager.domain.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 11/28/13.
 */
public class CategoryDataSource {

    private SQLiteDatabase database;
    private CategorySQLiteHelper dbHelper;
    private String[] allColumns={CategorySQLiteHelper.COLUMN_ID,CategorySQLiteHelper.COLUMN_CATEGORY_NAME};


    public CategoryDataSource(Context context) {
        dbHelper = new CategorySQLiteHelper(context);

    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createCategory(Category category)
    {
        try {
            open();
            if(null!=category)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(CategorySQLiteHelper.COLUMN_CATEGORY_NAME,category.getCategoryName());

                database.insert(CategorySQLiteHelper.TABLE_CATEGORY,null,contentValues);

            }
            else
                throw new RuntimeException("Category Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }

    }
    public void editCategory(Category category)
    {

        try {
            open();
            if(null!=category)
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(CategorySQLiteHelper.COLUMN_CATEGORY_NAME,category.getCategoryName());

                long updateId=database.update(CategorySQLiteHelper.TABLE_CATEGORY, contentValues, CategorySQLiteHelper.COLUMN_ID + "=" + category.getCategoryId().toString(), null);

            }
            else
                throw new RuntimeException("Category Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }


    }


    public void deleteCategory(Integer categoryId) {
        try {
            open();
            database.delete(CategorySQLiteHelper.TABLE_CATEGORY,CategorySQLiteHelper.COLUMN_ID + "="+categoryId,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }

    }

    public List<Category> getAllCategories()
    {
        List<Category> categoryList=new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + CategorySQLiteHelper.TABLE_CATEGORY;
        try {
            open();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Category category = new Category();
                    category.setCategoryId(cursor.getInt(0));
                    category.setCategoryName(cursor.getString(1));

                    // Adding contact to list
                    categoryList.add(category);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return categoryList;
    }


}
