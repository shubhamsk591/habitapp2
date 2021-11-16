package com.example.habitapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.achievement,container,false);
        EditText searchView=v.findViewById(R.id.SearchBar);
        ImageButton imageButton=v.findViewById(R.id.searchbutton);
        TableLayout tb =(TableLayout)v.findViewById(R.id.tablelayout);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= searchView.getText().toString();
                Databasehelper databasehelper=new Databasehelper(getContext());
                Cursor item=databasehelper.getItemID(name);
                int itemid=-1;
                while(item.moveToNext()){
                    itemid=item.getInt(0);
                    if(itemid>-1){
                        Cursor t=new DataBaseTracker(getContext()).getdetail(itemid,name);
                        while(t.moveToNext()){
                            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.tablerow,null,false);
                            TextView name1  = (TextView) tableRow.findViewById(R.id.name1);
                            TextView title  = (TextView) tableRow.findViewById(R.id.title1);


                            name1.setText(t.getString(4));
                            title.setText(t.getInt(5));
                            tb.addView(tableRow);

                        }
                        t.close();
                        }
                    else{
                        ToastMessage("Name not found ");
                    }
                }

            }

            private void ToastMessage(String st) {
                Toast.makeText(getContext(),st,Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}

/*private TableLayout tableLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout);

       data.moveToFirst();
       do {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            TextView name  = (TextView) tableRow.findViewById(R.id.name);
            TextView title  = (TextView) tableRow.findViewById(R.id.title);


            name.setText(data.getString(1));
            title.setText(data.getString(2));
            tableLayout.addView(tableRow);

        } while (data.moveToNext());
        data.close();*/