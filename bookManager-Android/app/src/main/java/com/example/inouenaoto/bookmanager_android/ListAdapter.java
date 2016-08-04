package com.example.inouenaoto.bookmanager_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class ListAdapter extends ArrayAdapter<CustomData> {

    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, int id, ArrayList<CustomData> objects) {
        super(context, id, objects);
        this.layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    R.layout.listitem,
                    parent,
                    false
            );
        }
        CustomData item = (CustomData) getItem(pos);
        ((ImageView) convertView.findViewById(R.id.icon))
                .setImageBitmap(item.getIcon());
        ((TextView) convertView.findViewById(R.id.title))
                .setText(item.getTitle());
        ((TextView) convertView.findViewById(R.id.price))
                .setText(item.getPrice() + item.getPriceAndTax());
        ((TextView) convertView.findViewById(R.id.date))
                .setText(item.getDate());
        return convertView;
    }
}