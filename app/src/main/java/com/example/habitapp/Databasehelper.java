

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

    private static final String Col9="BDate";

    public Databasehelper(@Nullable Context context) {
        super(context,TABLE_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  createtable="CREATE TABLE "+TABLE_Name+" (Id INTEGER Primary key AutoIncrement ,"+Col2+" TEXT NOT NULL ," +Col3+ " Text not null ,"+Col4+" TEXT ,"+Col5+" Text not null ,"+Col6+" Integer ,"+Col7+" Text ,"+Col8+" Integer , "+Col9+" Text);";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_Name);
    onCreate(sqLiteDatabase);
    }
    public boolean addDatabaseitem(String name,String question,String unit,String target,int remainder,String notes,int completed,String date){
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
        Log.d(TAG,"are inserting value");
        contentValues.put(Col9,date);
        long result=db.insert(TABLE_Name,null,contentValues);
        Log.d(TAG,"are returning boolean");
        return result != -1;
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("you","are getdata cursor");
        return db.rawQuery("SELECT * FROM "+TABLE_Name,null);
    }
    public Cursor getItemID(String na){
        Log.d(TAG,"bj"+na);
        SQLiteDatabase bdi=this.getWritableDatabase();
        String str1="SELECT "+Col1+" From "+TABLE_Name+" Where "+Col2+" = '"+na+"'";
        Log.d(TAG,"gasgh");
        return bdi.rawQuery(str1,null);


    }
    public Habits getvalueup(int i){
        SQLiteDatabase bdi2=this.getReadableDatabase();
        String st2="SELECT * FROM "+TABLE_Name+" WHERE "+Col1+" = "+i+";";
        Log.d("hjckj ","uchiu"+st2);
        Cursor s2=bdi2.rawQuery(st2,null);
        Log.d("hjckj ","uchiu"+s2);
        Habits hab=new Habits();
        while (s2.moveToNext()){
            i=s2.getInt(0);
            String name=s2.getString(1);
            Log.d("hjckj ","uchiu"+name);
            String question=s2.getString(2);
            String unit=s2.getString(3);
            String target=s2.getString(4);
            int rm=s2.getInt(5);
            String notes=s2.getString(6);
            int com=s2.getInt(7);

            hab=new Habits(i,name,question,unit,target,rm,notes,com);
            }
        s2.close();
        return hab;
    }

    public boolean updatedata(int i,String oldname,String name,String question,String unit,String target,int rm,String notes,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        Log.d("Name ","hahn: "+name);
        contentValues .put(Col3,question);
        contentValues.put(Col4,unit);
        contentValues.put(Col5,target);
        contentValues.put(Col6,rm);
        contentValues.put(Col7,notes);
        contentValues.put(Col8,com);
        Log.d(TAG,"are update value");
        String where =Col1 +" = '" +i+"' And "+Col2+" = ?";
        String args[]={oldname};
        long result=db.update(TABLE_Name,contentValues,where,args);
        db.close();
        Log.d(TAG,"are returning boolean");
        return result != -1;
    }
    public boolean deletedata(int i,String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String where =Col1 +" = '" +i+"' And "+Col2+" = ?";
        String args[]={name};
        long result=db.delete(TABLE_Name,where,args);
        db.close();
        Log.d(TAG,"are returning boolean");
        return result != -1;
    }
    public boolean setCompleted(int i,String name,int com){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col8,com);
        String where =Col1 +" = '" +i+"' And "+Col2+" = ?";
        String args[]={name};
        long result=db.update(TABLE_Name,contentValues,where,args);
        return result!=-1;

    }
}
/*public void update(int i,String oldname,String name,String question,String unit,String target,int rm,String notes,int com){
        SQLiteDatabase db2=this.getWritableDatabase();
        String query="Update "+TABLE_Name+" SET "+Col2+" = '"+name+ "',"+
                Col3+" = '"+question+"' , "+
                Col4+" = '"+unit+"' , "+
                Col5+" = '"+target+"' , "+
                Col6+" = "+rm+" , "+
                Col7+" = '"+notes+"' , "+
                Col8+" = "+com+"  "+
                " Where "+Col1+" = "+i +" And "+Col2+" = ' "+oldname +" '";
        Log.d(TAG,"bh "+query);
        db2.execSQL(query);
        Log.d(TAG,"bh "+query);
    }
    public void delete(int i,String name){
        SQLiteDatabase db2=this.getWritableDatabase();
        String query="Delete from "+TABLE_Name+
                " Where "+Col1+" = "+i +" And "+Col2+" = '"+name +" '";
        db2.execSQL(query);
        db2.close();

    }
    */
