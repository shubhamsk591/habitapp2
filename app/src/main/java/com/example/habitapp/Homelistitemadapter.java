package com.example.habitapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Homelistitemadapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;
    Habits ha;
    CheckBox checkBox;
    Databasehelper databasehelper;
    int com=0;
    int itemid=-1;
    public Homelistitemadapter(Context context, ArrayList<String> item) {
        super(context, R.layout.list_item, item);
        list = item;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.home_list_item, null);
            TextView name = convertView.findViewById(R.id.habitnamehomeitem);
            String na = list.get(position);
            name.setText(na);
            checkBox = convertView.findViewById(R.id.checkboxhomelist);
            setvaluecom(na);


            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isChecked()){
                        com=1;
                        updatedcom(na,com);
                        DataBaseTracker dataBaseTracker=new DataBaseTracker(getContext());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ", Locale.US);
                        String date = sdf.format(new Date());
                        databasehelper=new Databasehelper(getContext());
                        Cursor crdata1 = databasehelper.getItemID(na);
                        while (crdata1.moveToNext()) {
                            itemid = crdata1.getInt(0);
                            if (itemid > -1) {
                                boolean a=dataBaseTracker.setCompleted(itemid,na,date,com);
                                if(a){
                                    Toastmessage("Sucessfull to Change in Tracker");
                                }
                                else{
                                    Toastmessage("Failed to Change in Tracker");
                                }
                            }
                        }
                        compoundButton.setChecked(true);
                    }
                    else{
                        com=0;
                        updatedcom(na,com);
                        DataBaseTracker dataBaseTracker=new DataBaseTracker(getContext());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ",Locale.US);
                        String date = sdf.format(new Date());
                        databasehelper=new Databasehelper(getContext());
                        Cursor crdata1 = databasehelper.getItemID(na);
                        while (crdata1.moveToNext()) {
                            itemid = crdata1.getInt(0);
                            if (itemid > -1) {
                                boolean a=dataBaseTracker.setCompleted(itemid,na,date,com);
                                if(a){
                                    Toastmessage("Sucessfull to Change in Tracker");
                                }
                                else{
                                    Toastmessage("Failed to Change in Tracker");
                                }
                            }}
                        compoundButton.setChecked(false);
                    }
                }
            });
        }

        return convertView;
    }

    private void setvaluecom(String na) {
        ha=new Habits();
        databasehelper=new Databasehelper(getContext());
        Cursor crdata1 = databasehelper.getItemID(na);
        while (crdata1.moveToNext()) {
            itemid = crdata1.getInt(0);
            if (itemid > -1) {
                ha = databasehelper.getvalueup(itemid);
                com=ha.getCompleted();
                checkBox.setChecked(com == 1);
            }
        }

    }

    private void updatedcom(String na,int com) {
        databasehelper=new Databasehelper(getContext());
        Cursor crdata1 = databasehelper.getItemID(na);
        while (crdata1.moveToNext()) {
            itemid = crdata1.getInt(0);
            if (itemid > -1) {
                ha = databasehelper.getvalueup(itemid);
                boolean res=databasehelper.setCompleted(itemid,na,com);
                if(res){
                    Toastmessage("Sucessfully updated");
                }
                else{
                    Toastmessage("Failed to update");
                }
            }
            else{
                Toastmessage("Failed to find item");
            }
        }
    }

    private void Toastmessage(String wr) {
        Toast.makeText(getContext(), wr, Toast.LENGTH_SHORT).show();
    }
}
/*databasehelper = new Databasehelper(getContext());
            Cursor crdata1 = databasehelper.getItemID(na);
            while (crdata1.moveToNext()) {
                itemid = crdata1.getInt(0);
                if (itemid > -1) {
                    ha = databasehelper.getvalueup(itemid);
                    com = ha.getCompleted();
                    checkBox.setChecked(com == 1);
                }
            }*/