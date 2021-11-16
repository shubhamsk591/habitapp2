package com.example.habitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseDateUpdate extends SQLiteOpenHelper {
    private static final String TABLE_Name="Date1";
    private static final String Col1="Id";
    private static final String Col2="Date";
    public DataBaseDateUpdate(Context context) {
        super(context, TABLE_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key ,"+Col2+" TEXT NOT NULL  );";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
        onCreate(sqLiteDatabase);

    }
    public boolean addDatabaseitemdate(String next){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col1,1);
        contentValues.put(Col2,next);
        long result=db.insert(TABLE_Name,null,contentValues);
        return result != -1;
    }
    public boolean updatedate(String next){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,next);
        String where =Col1 +" = ' 1 ' ";
        long result=db.update(TABLE_Name,contentValues,where,null);
        return result != -1;
}
    public Cursor getdate(){
        SQLiteDatabase bdi2=this.getWritableDatabase();
        String st2="SELECT * FROM "+TABLE_Name+" WHERE "+Col1+" = '1'";
        Log.d("hjckj ","uchiu"+st2);
        String prev=" ";
        Cursor s2=bdi2.rawQuery(st2,null);
        return s2;}
}
