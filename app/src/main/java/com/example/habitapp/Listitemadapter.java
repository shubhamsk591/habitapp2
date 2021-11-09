package com.example.habitapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Listitemadapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public Listitemadapter(Context context, ArrayList<String> item) {
        super(context, R.layout.list_item, item);
        list = item;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            TextView name=convertView.findViewById(R.id.habitnameitem);
            String na= list.get(position);
            name.setText(na);
            ImageButton up=convertView.findViewById(R.id.updatebuttonlist);
            ImageButton del=convertView.findViewById(R.id.deletebuttonlist);

            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String na= list.get(position);
                    Databasehelper databasehelper=new Databasehelper(getContext());
                    Cursor crdata1 = databasehelper.getItemID(na);
                    int itemid = -1;
                    while (crdata1.moveToNext()) {
                        itemid = crdata1.getInt(0);
                        if (itemid > -1) {
                            Intent updel = new Intent(getContext(), Updatahabit.class);
                            updel.putExtra("Id", itemid);
                            updel.putExtra("Name", na);
                            context.startActivity(updel);
                        } else {
                            Toastmessage("wrong id");
                        }
                    }

                }
            });

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=list.get(position);
                    int itemid=-1;
                    Databasehelper databasehelper=new Databasehelper(getContext());
                    Cursor cursor=databasehelper.getItemID(name);
                    Log.d("bsj","djs");
                    while (cursor.moveToNext()){
                        itemid=cursor.getInt(0);
                        if(itemid >-1) {
                            boolean del = databasehelper.deletedata(itemid, name);
                            if (del) {
                                Toastmessage("Sucessfully deleted ");
                                Intent ab = new Intent(getContext(), MainActivity.class);
                                context.startActivity(ab);
                            }} else {
                                Toastmessage("Failed to delete ");
                                Intent ab = new Intent(getContext(), MainActivity.class);
                                context.startActivity(ab);
                            }
                        }}
        });
        }
        return convertView;
}

    private void Toastmessage(String wr) {
        Toast.makeText(getContext(), wr, Toast.LENGTH_SHORT).show();
    }
}