package com.example.inouenaoto.bookmanager_android;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccountRegister extends AsyncTask<String, Integer, String> {
    StringBuffer buffer;

    @Override
    protected String doInBackground(String... params) {
        String mailAddress = params[0];
        String mPassword = params[1];

        HttpURLConnection conn = null;
        try {
            URL url = new URL(MyApplication.getContext().getResources()
                    .getString(R.string.account_register_url));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000);
            conn.setDoOutput(true);

            String postData = MyApplication.getContext()
                    .getString(R.string.register_post_data,mailAddress,mPassword);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = conn.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return buffer.toString();
    }
}