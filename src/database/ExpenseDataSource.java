package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.emanager.Expense;
import com.example.emanager.ExpenseNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cambas on 9/25/13.
 */
public class ExpenseDataSource {

    private SQLiteDatabase database;
    private ExpenseSQLiteHelper dbHelper;
    private String[] allColumns={ExpenseSQLiteHelper.COLUMN_ID,ExpenseSQLiteHelper.COLUMN_NAME,ExpenseSQLiteHelper.COLUMN_AMOUNT,
            ExpenseSQLiteHelper.COLUMN_DATE,ExpenseSQLiteHelper.COLUMN_LOCATION,ExpenseSQLiteHelper.COLUMN_MODE};


    public ExpenseDataSource(Context context) {
        dbHelper = new ExpenseSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createExpense(Expense expense)
    {
        if(null!=expense)
        {
        ContentValues contentValues=new ContentValues();

            contentValues.put(ExpenseSQLiteHelper.COLUMN_NAME,expense.getExpenseName());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_AMOUNT,expense.getAmount());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_DATE,expense.getDate().toString());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_LOCATION,expense.getLocation());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_MODE,expense.getMode());
        long insertId=database.insert(ExpenseSQLiteHelper.TABLE_EXPENSES,null,contentValues);
            System.out.print(insertId);
        }
        else
            throw new ExpenseNotFoundException("Expense Not Found");
    }
    public void editExpense(Expense expense)
    {
        if(null!=expense)
        {
            ContentValues contentValues=new ContentValues();
            contentValues.put(ExpenseSQLiteHelper.COLUMN_NAME,expense.getExpenseName());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_AMOUNT,expense.getAmount());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_DATE,expense.getDate().toString());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_LOCATION,expense.getLocation());
            contentValues.put(ExpenseSQLiteHelper.COLUMN_MODE,expense.getMode());
            long updateId=database.update(ExpenseSQLiteHelper.TABLE_EXPENSES,contentValues,ExpenseSQLiteHelper.COLUMN_ID +"="+expense.getExpenseId().toString(),null);
            System.out.print(updateId);
        }
        else
            throw new RuntimeException("Expense Not Found");
    }


        public void deleteExpense(Integer expenseId)
    {
        System.out.println("Expense deleted with ID:" + expenseId);
        database.delete(ExpenseSQLiteHelper.TABLE_EXPENSES,ExpenseSQLiteHelper.COLUMN_ID + "="+expenseId,null);

    }

    public List<Expense> getAllExpense()
    {
        List<Expense> expenseList=new ArrayList<Expense>();

        Cursor cursor=database.query(ExpenseSQLiteHelper.TABLE_EXPENSES,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Expense expense=cursorToExpense(cursor);
            expenseList.add(expense);
        }
        cursor.close();
        return expenseList;
    }

    public List<Expense> getExpenseByName(String regex)
    {
        List<Expense> expenseListByName=new ArrayList<Expense>();
        Cursor cursor=database.query(ExpenseSQLiteHelper.TABLE_EXPENSES,allColumns,ExpenseSQLiteHelper.COLUMN_NAME+" like " + regex,null,null,null,null);
        while(!cursor.isAfterLast())
        {
            Expense expense=cursorToExpense(cursor);
            expenseListByName.add(expense);
        }
        cursor.close();
        return expenseListByName;

    }



    private Expense cursorToExpense(Cursor cursor)
    {
        Expense expense=new Expense();
        expense.setExpenseId(cursor.getInt(0));
        expense.setExpenseName(cursor.getString(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_NAME)));
        expense.setAmount(cursor.getDouble(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_AMOUNT)));
        expense.setDate(cursor.getString(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_DATE)));
        expense.setLocation(cursor.getString(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_LOCATION)));
        expense.setMode(cursor.getString(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_MODE)));

        return expense;

    }
}
