package com.example.habitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseTracker extends SQLiteOpenHelper {
    private static final String TABLE_Name="Tracker1";
    private static final String Col1="Id";
    private static final String Col2="Name";
    private static final String Col3="Date";
    private static final String Col4="Completed";
    public DataBaseTracker(@Nullable Context context) {
        super(context, TABLE_Name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" TEXT NOT NULL ," +Col3+ " Text not null ,"+Col4+" int not null );";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
        onCreate(sqLiteDatabase);

    }
    public boolean addDatabaseitemtracker(String name,String date,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        Log.d("Name ","hahn: "+name);
        contentValues .put(Col3,date);
        contentValues.put(Col4,com);
        long result=db.insert(TABLE_Name,null,contentValues);
        return result != -1;
    }
    public boolean setCompleted(String name,String date,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col4,com);
        String where =Col2 +" = ? And "+Col2+" = ?";
        String args[]={name,date};
        long result=db.update(TABLE_Name,contentValues,where,args);
        return result!=-1;
}

}
