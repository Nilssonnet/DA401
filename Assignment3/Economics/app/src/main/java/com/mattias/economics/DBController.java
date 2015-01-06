package com.mattias.economics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mattias on 2015-01-06.
 */
public class DBController extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String TAG ="Dbhelper" ;
    private static final String NAME = "COMPANY_DATABASE";
    private static final String TABLE_INCOME = "Incomes";
    private static final String TABLE_EXPENSE = "Expenses";
    private static final int VERSION =1;

    private static final String CREATETABLE_INCOME = "CREATE TABLE Incomes"  +
            "(_id integer primary key autoincrement, " +
            "Date text not null, " +
            "Amount int not null,"+
            "Title text not null);";

    private static final String CREATETABLE_EXPENSE = "CREATE TABLE Expenses"+
            "(_id integer primary key autoincrement, " +
            "Date text not null, " +
            "Amount int not null,"+
            "Title text not null);";

    public DBController(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETABLE_INCOME);
        db.execSQL(CREATETABLE_EXPENSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"Upgrading from version"+oldVersion+"to"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        onCreate(db);
    }

    public void open(){
        db = getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long dataIncomes(String title, String amount, String date) {
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Date",date);
        values.put("Amount", Integer.parseInt(amount));
        return db.insert("Incomes",null,values);
    }

    public long dataExpenses(String title, String amount, String date) {
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Date",date);
        values.put("Amount", Integer.parseInt(amount));
        return db.insert("Expenses",null,values);
    }

    public Cursor getIncomes(){
        return db.query(
                "Incomes",
                new String[]{"_id", "Date", "Amount","Title"},
                null, null, null, null, null);
    }

    public Cursor getExpenses() {
        return db.query(
                "Expenses",
                new String[]{"_id", "Date", "Amount", "Title"},
                null, null, null, null, null);

    }

    public int getIncomesAmount(){
        return 1000;
    }

    public int getExpensesAmount(){
        return 500;
    }
}