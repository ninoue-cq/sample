package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

//ピッカーのデータを取得しエディットテキストに反映させるためのクラス
public class PickerSetting {
    public void pickerAppear(Activity activity,final EditText editText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final DatePicker datePicker = new DatePicker(activity);
        builder.setView(datePicker);
        builder.setTitle(R.string.day_select);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                editText.setText(year + "/" + month + "/" + day);
            }
        });
        builder.setNegativeButton(R.string.back, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}