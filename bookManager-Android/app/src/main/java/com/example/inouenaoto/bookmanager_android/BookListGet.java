
package com.example.inouenaoto.bookmanager_android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookListGet extends AsyncTask<Void, Void, StringBuffer> {
    StringBuffer buffer;
    private APIListener mAPIListener;

    public void setAPIListener(APIListener apiListener) {
        this.mAPIListener = apiListener;
    }

//    BookListFragment bookListFragment = new BookListFragment();
//    private int listCount = bookListFragment.mDisplayCount;

    @Override
    protected StringBuffer doInBackground(Void... voids) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(MyApplication.getContext()
                    .getResources().getString(R.string.book_list_url));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);
            String postData = "page=1-100";
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

        return buffer;
    }

    @Override
    protected void onPostExecute(StringBuffer result) {
        try {
            mAPIListener.didConnection(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
