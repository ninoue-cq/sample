package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        setTitle(R.string.account_login_title);

        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logintButtonTapped();
            }
        });
    }

    public void logintButtonTapped() {
        EditText mailText = (EditText) findViewById(R.id.mail_adress);
        final String mailAddress = mailText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.password);
        final String password = passwordText.getText().toString();

        if (mailAddress.length() == 0 || password.length() == 0) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountLoginActivity.this);
            alertDialog.setMessage(R.string.not_entered_message);
            alertDialog.setPositiveButton(R.string.confirm,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //特に処理は行わないがこれがないとエラーが出てしまう
                        }
                    });
            alertDialog.show();
        } else {
            new Login(AccountLoginActivity.this).execute(mailAddress, password);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountLoginActivity.this);
            new AlertDialog.Builder(AccountLoginActivity.this);
            alertDialog.setMessage(R.string.login_complete);
            alertDialog.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface daialg, int which) {
                    Intent intent = new Intent(AccountLoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    }
}
