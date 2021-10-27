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

public class Fetchdataformotivation extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataparsed="";
    String singleparsed="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("https://raw.githubusercontent.com/shubhamsk591/jsonfiles/355c69b75ccf32e7b5d2a2d7dc0437651e0e1d32/MotivationInsight.json");
            Log.d("car","url"+url);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            Log.d("car","url"+httpURLConnection);
            InputStream inputStream=httpURLConnection.getInputStream();
            Log.d("car","url"+inputStream);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            Log.d("car","url"+bufferedReader);
            String line="";
            Log.d("car","line"+line);
            while (line !=null){
                line = bufferedReader.readLine();
                Log.d("car","url"+line);

                data=data + line;
                Log.d("car","Datat :"+data);
            }
            JSONArray JSArray=new JSONArray(data);
            for(int i=0;i< JSArray.length();i++){
                JSONObject jsonObject= (JSONObject) JSArray.get(i);
                singleparsed="Quotes:"+jsonObject.get("quote")+"\n"+
                        "Author:"+jsonObject.get("author")+"\n";
                dataparsed=dataparsed + singleparsed +"\n";
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        MotivationInsight.data.setText(this.dataparsed);
    }
}
