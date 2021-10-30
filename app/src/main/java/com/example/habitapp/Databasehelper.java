package com.example.habitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {
    private static final  String TAG="Databasehelper";
    private static final String TABLE_Name="Person1";
    private static final String Col1="Id";
    private static final String Col2="Name";
    private static final String Col3="Question";
    private static final String Col4="Unit";
    private static final String Col5="Target";
    private static final String Col6="Reminder";
    private static final String Col7="Notes";
    private static final String Col8="Completed";

    public Databasehelper(@Nullable Context context) {
        super(context,TABLE_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" TEXT Not null ," +Col3+ " Text not null ,"+Col4+" TEXT ,"+Col5+" Text not null ,"+Col6+" Boolean,"+Col7+" Text ,"+Col8+" boolean );";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
    onCreate(sqLiteDatabase);
    }
    public boolean addDatabaseitem(String name,String question,String unit,String target,boolean remainder,String notes,boolean completed){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        Log.d("Name ","hahn: "+name);
        contentValues .put(Col3,question);
        contentValues.put(Col4,unit);
        contentValues.put(Col5,target);
        contentValues.put(Col6,remainder);
        contentValues.put(Col7,notes);
        contentValues.put(Col8,completed);
        Log.d("you","are inserting value");

        long result=db.insert(TABLE_Name,null,contentValues);
        Log.d("you","are returning boolean");
        return result != -1;
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("you","are getdata cursor");
        return db.rawQuery("SELECT * FROM "+TABLE_Name,null);
    }
}
