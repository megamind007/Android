package com.sakib.servertest;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Registar extends AppCompatActivity {

    EditText RetUsername, Retpass, RetShopname, RetMail, RetContact, RetAddress;
    TextView register;
    Button regBtn;
    String name, pass, shopName, contact, mail, address;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        register=(TextView)findViewById(R.id.textView5);
        Typeface tp=Typeface.createFromAsset(getAssets(),"sansationregular.ttf");
        register.setTypeface(tp);
        RetUsername = (EditText) findViewById(R.id.RetUsername);
        Retpass = (EditText) findViewById(R.id.RetPass);
        RetShopname = (EditText) findViewById(R.id.RetShopname);
        RetMail = (EditText) findViewById(R.id.RetMail);
        RetContact = (EditText) findViewById(R.id.RetContact);
        RetAddress = (EditText) findViewById(R.id.RetAddress);
        regBtn = (Button) findViewById(R.id.Rbtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = RetUsername.getText().toString();
                pass = Retpass.getText().toString();
                shopName = RetShopname.getText().toString();
                contact = RetContact.getText().toString();
                mail = RetMail.getText().toString();
                address = RetAddress.getText().toString();

                String method = "register";
                BackgroundWork prc=new BackgroundWork(Registar.this);
                prc.execute(method,name,pass,shopName,contact,mail,address);
                finish();




            }
        });



    }


}
