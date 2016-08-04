package com.example.inouenaoto.bookmanager_android;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookDataEdit extends AsyncTask<String, Integer, String> {
    StringBuffer buffer;
    Context context;

    public BookDataEdit(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String mTitle = params[0];
        String mPrice = params[1];
        String mDate = params[2];
        String mBookId = params[3];

        HttpURLConnection connection = null;
        try {
            URL url = new URL(MyApplication.getContext().getResources()
                    .getString(R.string.book_edit_url));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);

            String postData = MyApplication.getContext().getResources()
                    .getString(R.string.edit_post_data, mBookId, mTitle, mPrice, mDate);
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