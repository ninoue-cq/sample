package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;

public class BookAddActivity extends Activity {

    private static final int REQUEST_GALLERY = 0;
    public EditText mSetDateText;
    PickerSetting pickerSetting = new PickerSetting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);
        setTitle(R.string.book_add_title);

        mSetDateText = (EditText) findViewById(R.id.add_book_date);
        mSetDateText.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                pickerSetting.pickerAppear(BookAddActivity. this,mSetDateText);
            }
        });

        //画像添付ボタンの処理
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close_button:
                finish();
                break;
            case R.id.save_button:
                //書籍追加の処理
                addBook();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        ImageView bookImageView = (ImageView) findViewById(R.id.book_image);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bookImage = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                // 選択した画像を表示
                bookImageView.setImageBitmap(bookImage);
            } catch (Exception e) {

            }
        }
    }

    public void addBook(){
        //書籍追加の処理
        EditText bookTitle = (EditText) findViewById(R.id.add_book_title);
        String addBookTitle = bookTitle.getText().toString();

        EditText bookPrice = (EditText) findViewById(R.id.add_book_price);
        String addBookPrice = bookPrice.getText().toString();

        EditText bookDate = (EditText) findViewById(R.id.add_book_date);
        String addBookDate = bookDate.getText().toString();

        if (addBookTitle.length() == 0 || addBookPrice.length() == 0 || addBookDate.length() == 0) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookAddActivity.this);
            alertDialog.setMessage(R.string.not_entered_message);
            alertDialog.setPositiveButton(R.string.confirm,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            alertDialog.show();
        } else {
            new BookDataAdd().execute(addBookTitle, addBookPrice, addBookDate);
            Intent intent = new Intent(BookAddActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
