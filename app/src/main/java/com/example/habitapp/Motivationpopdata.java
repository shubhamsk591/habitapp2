package com.example.habitapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
public class Motivationpopdata extends AsyncTask<Void,Void,Void> {
    String data="";
    String singleparsed="";
    int i;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("https://raw.githubusercontent.com/shubhamsk591/jsonfiles/355c69b75ccf32e7b5d2a2d7dc0437651e0e1d32/MotivationInsight.json");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line !=null){
                line = bufferedReader.readLine();
                data=data + line;
            }
            JSONArray JSArray=new JSONArray(data);
            JSONObject jsonObject= (JSONObject) JSArray.get(geti());
            Log.d("car","Text i  "+i);
            singleparsed="Quotes:"+jsonObject.get("quote")+"\n"+
                    "Author:"+jsonObject.get("author")+"\n";

        } catch (IOException | JSONException e) {
            e.printStackTrace();

        }
        return null;

    }

    int geti(){
        return i;
    }
    void setvalue(int a){
        i=a;
    }

    @Override
    protected void onPostExecute(Void unused) {
        Popup.data.setText(this.singleparsed);
    }

}
