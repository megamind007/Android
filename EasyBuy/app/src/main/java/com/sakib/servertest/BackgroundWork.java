package com.sakib.servertest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by sakib on 19-Mar-16.
 */
public class BackgroundWork extends AsyncTask<String, Void, String> {

    Context ctx;
    AlertDialog alertDialog;

    public BackgroundWork(Context ctx){
        this.ctx=ctx;
    }



    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");

    }


    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://chowdhurysakib.info/android/register.php";
        String login_url="http://chowdhurysakib.info/android/login.php";
        String order="http://chowdhurysakib.info/android/order.php";

        String method=params[0];
        if(method.equals("register")){
            String name=params[1];
            String pass=params[2];
            String shopName=params[3];
            String contact=params[4];
            String mail=params[5];
            String address=params[6];

            try {
                URL url =new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                data += "&" + URLEncoder.encode("shopName", "UTF-8") + "=" + URLEncoder.encode(shopName, "UTF-8");
                data += "&" + URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8");
                data += "&" + URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "registration success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login")){
            String login_name=params[1];
            String login_pass=params[2];

            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data  = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8");
                data += "&" + URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(method.equals("order")){
            String order_detailes=params[1];

            try {
                URL url=new URL(order);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data  = URLEncoder.encode("detailes", "UTF-8") + "=" + URLEncoder.encode(order_detailes, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "order complete...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    //MainActivity mainActivity=new MainActivity();
    @Override
    protected void onPostExecute(String result) {
        if (result=="registration success...") {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }else if (result.equals("success")) {
           // mainActivity.lgnUsername.setText("");
           // mainActivity.lgnPass.setText("");
            Intent i=new Intent(ctx,profile.class);
            ctx.startActivity(i);
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


           // startActivity(i);

        }
        else if (result.equals("order complete...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {
            alertDialog.setMessage(result);
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            alertDialog.show();
        }
    }
}
