package com.sakib.servertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView regTv,brandName;
    EditText lgnUsername,lgnPass;
    Button lgnBtn;
    String login_name,login_pass;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font.otf");
        Typeface typeface1=Typeface.createFromAsset(getAssets(),"sansationregular.ttf");
        brandName=(TextView)findViewById(R.id.textView);
        brandName.setTypeface(typeface);
        userLocalStore=new UserLocalStore(this);
        regTv=(TextView)findViewById(R.id.regTv);
        lgnUsername=(EditText)findViewById(R.id.lgnUsername);
        lgnPass=(EditText)findViewById(R.id.lgnPass);
        lgnBtn=(Button)findViewById(R.id.loginBtn);
        regTv.setTypeface(typeface1);
        //lgnBtn.setTypeface(typeface1);

        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.setUserLoggedIn(true);
                User user;
                login_name=lgnUsername.getText().toString();
                login_pass=lgnPass.getText().toString();
                String method="login";
                BackgroundWork prc=new BackgroundWork(MainActivity.this);
                prc.execute(method,login_name,login_pass);
                lgnUsername.setText("");
                lgnPass.setText("");
                finish();
            }
        });




        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Registar.class);
                startActivity(i);
            }
        });
    }

}
