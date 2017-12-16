package com.sakib.servertest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Log extends AppCompatActivity {


    String json_string,target_string,newString;
    ArrayList<String> arrayList;
    ArrayAdapter<String>arrayAdapter;
    JSONArray jsonArray;
    ListView lv;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //tvLog= (TextView) findViewById(R.id.tvLog);
        //showLogBtn= (Button) findViewById(R.id.btnLog);
        lv= (ListView) findViewById(R.id.listView3);
        arrayList=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(arrayAdapter);
        new backgroundTask().execute();



        
    }


    class backgroundTask extends AsyncTask<Void ,Void, String> {


        String json_url;

        @Override
        protected void onPreExecute() {
            json_url="http://chowdhurysakib.info/android/log_get_data.php";

        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStreamReader=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStreamReader));
                StringBuilder stringBuilder=new StringBuilder();
                while ((json_string=bufferedReader.readLine())!=null){

                    stringBuilder.append(json_string + "\n");
                    bufferedReader.close();
                    inputStreamReader.close();
                    httpURLConnection.disconnect();

                    return stringBuilder.toString().trim();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            newString=result;
            try {
                jsonObject=new JSONObject(newString);
                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                String details;
                while(count<jsonArray.length()) {

                    JSONObject jo=jsonArray.getJSONObject(count);
                    details=jo.getString("order_details");
                   // price=jo.getString("price");



                    arrayList.add(details);
                    arrayAdapter.notifyDataSetChanged();
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
    }
}
