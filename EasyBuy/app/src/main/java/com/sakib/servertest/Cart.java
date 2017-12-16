package com.sakib.servertest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {


    public static ArrayList<String> myList;
    public static ArrayAdapter<String> myadapter;
    ListView listView;
    Button mybtn,removeBtn,confirmBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //costStr=getIntent().getExtras().getString("cost");
        myList = (ArrayList<String>) getIntent().getSerializableExtra("list");
        //mybtn=(Button)findViewById(R.id.continueShopping);

        removeBtn=(Button)findViewById(R.id.cartRemoveBtn);
        confirmBtn=(Button)findViewById(R.id.cartConfirmBtn);

        listView=(ListView)findViewById(R.id.listView2);
        myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        listView.setAdapter(myadapter);
        // myadapter.notifyDataSetChanged();





        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myList.remove(position);
                myadapter.notifyDataSetChanged();
                ItemDetails itemDetails = new ItemDetails();
                itemDetails.item = myList;
                //itemDetails.arrayList=myList;
                return true;
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.clear();
                myadapter.notifyDataSetChanged();
                ItemDetails itemDetails = new ItemDetails();
                itemDetails.item = myList;
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "order";
                String y="";
                for(int i=0;i<myList.size();i++){
                    y+=String.valueOf(myList.get(i))+"\n";
                }
                if(y!="") {
                    Toast.makeText(Cart.this, y, Toast.LENGTH_LONG).show();
                    BackgroundWork prc = new BackgroundWork(Cart.this);
                    prc.execute(method, y);
                    myList.clear();
                    myadapter.notifyDataSetChanged();
                    ItemDetails itemDetails = new ItemDetails();
                    itemDetails.item = myList;

                    Intent i = new Intent(Cart.this, profile.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Cart.this, "Cart is Empty!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
