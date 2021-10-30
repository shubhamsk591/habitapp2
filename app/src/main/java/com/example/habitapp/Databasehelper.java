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
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" TEXT Not null ," +Col3+ " Text not null ,"+Col4+" TEXT ,"+Col5+" Text not null ,"+Col6+" Integer ,"+Col7+" Text ,"+Col8+" Integer);";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
    onCreate(sqLiteDatabase);
    }
    public boolean addDatabaseitem(String name,String question,String unit,String target,int remainder,String notes,int completed){
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
    public Cursor getItemID(String na){
        SQLiteDatabase bdi=this.getWritableDatabase();
        String str1="SELECT "+Col1+" From "+TABLE_Name+" Where "+Col2+" = '"+na+"'";
        return bdi.rawQuery(str1,null);

    }
    public void getvalueup(int i,String name,String question,String unit,String target,int rm,String notes,int com){
        String st2="SELECT "+Col2+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        SQLiteDatabase bdi2=this.getWritableDatabase();
        Cursor s2=bdi2.rawQuery(st2,null);
        name=s2.getString(1);
        st2="SELECT "+Col3+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        question=s2.getString(2);
        st2="SELECT "+Col4+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        unit=s2.getString(3);
        st2="SELECT "+Col5+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        target=s2.getString(4);
        st2="SELECT "+Col6+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        rm=s2.getInt(5);
        st2="SELECT "+Col7+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        notes=s2.getString(6);
        st2="SELECT "+Col8+" From "+TABLE_Name+" Where "+Col1+" = "+i+";";
        s2= bdi2.rawQuery(st2,null);
        com=s2.getInt(7);


    }
    public void update(int i,String oldname,String name,String question,String unit,String target,int rm,String notes,int com){
        SQLiteDatabase db2=this.getWritableDatabase();
        String query="Update "+TABLE_Name+" SET "+Col2+" = "+name+ ","+
                Col3+" = "+question+" , "+" , "+
                Col4+" = "+unit+" , "+
                Col5+" = "+target+" , "+
                Col6+" = "+rm+" , "+
                Col7+" = "+notes+" , "+
                Col8+" = "+com+" , "+
                " Where "+Col1+" = "+i +" And "+Col2+" = "+oldname +" ;";
        db2.execSQL(query);
    }
    public void delete(int i,String name){
        SQLiteDatabase db2=this.getWritableDatabase();
        String query="Delete from "+TABLE_Name+
                " Where "+Col1+" = "+i +" And "+Col2+" = "+name +" ;";
        db2.execSQL(query);

    }
}
