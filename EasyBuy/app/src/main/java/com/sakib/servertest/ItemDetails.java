package com.sakib.servertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails extends AppCompatActivity {
    String mystr,mystr2;
    TextView tv,tv2,tv3,tv4;
    Button plusBtn,minusBtn,addToCart;

    public static ArrayList<String> item;
    public static ArrayList<String> arrayList;

    int i=1;
    int totalCost=0;
    static public int overAllCost=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        tv=(TextView)findViewById(R.id.tvOrderpname);
        tv2=(TextView)findViewById(R.id.tvOrederprice);
        tv3=(TextView)findViewById(R.id.tvQuantity);
        tv4=(TextView)findViewById(R.id.tvTotalcost);
        if(item==null){
            item =new ArrayList<String>();
        }

        addToCart=(Button)findViewById(R.id.btnAddToCart);
        plusBtn=(Button)findViewById(R.id.button);
        minusBtn=(Button)findViewById(R.id.button2);


        mystr=getIntent().getExtras().getString("itemName");
        mystr2=getIntent().getExtras().getString("itemPrice");
        final int price=Integer.parseInt(mystr2);
        totalCost=price;

        tv3.setText(""+i);
        tv4.setText(""+totalCost);

        tv.setText(mystr);
        tv2.setText(mystr2);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                totalCost = price * i;
                overAllCost+=totalCost;
                tv3.setText("" + i);
                tv4.setText("" + totalCost);
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i > 1 ){
                    i--;
                    totalCost = price * i;
                    overAllCost-=totalCost;
                    tv3.setText("" + i);
                    tv4.setText("" + totalCost);
                }else{
                    Toast.makeText(ItemDetails.this,"Quantity must be more then 0",Toast.LENGTH_LONG).show();
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile prf=new profile();
                prf.count=1;
                String x= "Product Name : "+mystr+", Price : "+mystr2+", Quantity : "+i+", Toatl Cost : "+totalCost;
                item.add(x);

                Intent i=new Intent(ItemDetails.this,Cart.class);

                i.putExtra("list",item);

                startActivity(i);
                finish();
            }
        });



    }
}
