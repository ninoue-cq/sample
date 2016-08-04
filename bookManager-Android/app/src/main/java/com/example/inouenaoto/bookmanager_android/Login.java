package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AsyncTask<String, Integer, String> {
    StringBuffer buffer;
    Context context;

    public String mMailAddress;
    public String mPassword;
    public Login(Context context) {
        this.context = context;
    }
    Context ctx;
    @Override
    protected String doInBackground(String... params) {
        mMailAddress = params[0];
        mPassword = params[1];
        //String sample = String.getStrin():
        HttpURLConnection connection = null;
        try {
            //URL url = new URL("http://app.com/account/login");
            URL url = new URL(MyApplication.getContext().getResources().
                    getString(R.string.account_login_url));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);

            String postData = MyApplication.getContext()
                    .getString(R.string.login_post_data, mMailAddress, mPassword);
            Log.d("Login post;", postData);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return buffer.toString();
    }
}