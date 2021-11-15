package com.example.habitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseTracker extends SQLiteOpenHelper {
    private static final String TABLE_Name="Tracker1";
    private static final String Col1="Id";
    private static final String Col2="IdName";
    private static final String Col3="Name";
    private static final String Col4="Date";
    private static final String Col5="Completed";
    public DataBaseTracker(@Nullable Context context) {
        super(context, TABLE_Name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" Integer NOT NULL ,"+Col3+" Text not null , " +Col4+ " Text not null ,"+Col5+" int not null );";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
        onCreate(sqLiteDatabase);

    }
    public boolean addDatabaseitemtracker(int id,String name,String date,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,id);
        contentValues.put(Col3,name);
        Log.d("Name ","hahn: "+name);
        contentValues .put(Col4,date);
        contentValues.put(Col5,com);
        long result=db.insert(TABLE_Name,null,contentValues);
        return result != -1;
    }
    public boolean setCompleted(int idname,String name,String date,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col4,com);
        String where =Col2 +" = '"+idname+"' And "+Col3 +" = ? And "+Col4+" = ?";
        String args[]={name,date};
        long result=db.update(TABLE_Name,contentValues,where,args);
        return result!=-1;
}
    public Cursor getdetail(int id,String name){
        SQLiteDatabase bdi2=this.getReadableDatabase();
        String st2="SELECT * FROM "+TABLE_Name+" WHERE "+Col2+" = "+id+" And "+Col3+" = '"+name+"' ;";
        Log.d("hjckj ","uchiu"+st2);
        Cursor s2=bdi2.rawQuery(st2,null);
        Log.d("hjckj ","uchiu"+s2);
        return s2;
    }

    /*public int getComplete(int id, String name, String Date)
    {
        SQLiteDatabase bdi2=this.getReadableDatabase();
        String st2="SELECT * FROM "+TABLE_Name+" WHERE "+Col3+" = '"+name+"' And "+Col4+" = '"+name+"' ;";
        Log.d("hjckj ","uchiu"+st2);
        Cursor s2=bdi2.rawQuery(st2,null);
        int com=0;
        while(s2.moveToNext()){
            com=s2.getInt(5);
        }
        return com;

    }*/
}
