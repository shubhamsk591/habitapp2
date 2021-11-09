package com.example.habitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseLoginhelper extends SQLiteOpenHelper {
    public static final String DBNAme="Login.db";
    private static final String TABLE_Name="Login1";
    private static final String Col2="Username";
    private static final String Col3="Password";
    public DatabaseLoginhelper(Context context) {

        super(context, DBNAme,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" TEXT NOT NULL ," +Col3+ " Text not null );";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
        onCreate(sqLiteDatabase);

    }
    public boolean addDatabaselogin(String name,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        Log.d("Name ","hahn: "+name);
        contentValues .put(Col3,password);
        long result=db.insert(TABLE_Name,null,contentValues);
        Log.d("TAG","are returning boolean");
        return result != -1;

    }
   public boolean checkusername(String user){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from "+TABLE_Name+" where "+Col2 +" = ? ",new String[] {user});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;}
   }
   public boolean checkuserandpass(String user,String pass){
       SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       Cursor cursor=sqLiteDatabase.rawQuery("Select * from "+TABLE_Name+" where "+Col2 +" = ? AND "+Col3+" =? ",new String[] {user,pass});
       if(cursor.getCount()>0){
           cursor.close();
           return true;
       }
       else{
           cursor.close();
           return false;}
   }
}
