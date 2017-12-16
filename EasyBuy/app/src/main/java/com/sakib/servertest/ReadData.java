package com.sakib.servertest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ReadData extends AppCompatActivity {



    String json_string;
    //TextView tv;
   // ArrayList<String> items;
   // ArrayAdapter<String> itemAdapter;
    ListView lvitem;
    JSONArray jsonArray;
    DetailsAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        json_string=getIntent().getExtras().getString("json_data");
        detailsAdapter=new DetailsAdapter(this,R.layout.row_layout);
        //tv=(TextView)findViewById(R.id.tvJson);
        lvitem=(ListView)findViewById(R.id.listView);
       // items=new ArrayList<String>();
       // itemAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lvitem.setAdapter(detailsAdapter);

        try {
            JSONObject jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
        int count=0;
            String name,price;
        while(count<jsonArray.length()) {

            JSONObject jo=jsonArray.getJSONObject(count);
            name=jo.getString("name");
            price=jo.getString("price");
            details dtl=new details(name,price);
            detailsAdapter.add(dtl);


            //items.add(name);
            count++;
        }

        } catch (JSONException e) {
            e.printStackTrace();

        }


        lvitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int i=0;
                if(position>-1){
                    i=1;
                }
                switch (i) {
                    case 1:
                        TextView textView = (TextView) view.findViewById(R.id.tvProduct);
                        String selectedFromList=textView.getText().toString();
                        TextView textView2 = (TextView) view.findViewById(R.id.tvPrice);
                        String selectedFromList2=textView2.getText().toString();
                        //Toast.makeText(ReadData.this,selectedFromList,Toast.LENGTH_LONG).show();
                        Intent newActivity = new Intent(ReadData.this, ItemDetails.class);
                        newActivity.putExtra("itemName",selectedFromList);
                        newActivity.putExtra("itemPrice",selectedFromList2);
                        startActivity(newActivity);
                        break;
                }

            }
        });
    }


}
