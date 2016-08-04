package com.example.inouenaoto.bookmanager_android;

import android.graphics.Bitmap;

public class CustomData {
    private Bitmap mIcon;
    private String mId;
    private String mTitle;
    private String mPrice;
    private String mPriceAndTax;
    private String mDate;

    public String getPrice() {
        return mPrice;
    }
    public void setPrice(String price) {
        mPrice = price;
    }

    public String getPriceAndTax() {
        return mPriceAndTax;
    }
    public void setPriceAndTax(String priceAndTax) {
        mPriceAndTax = priceAndTax;
    }

    public String getDate() {
        return mDate;
    }
    public void setDate(String date) {mDate = date;}

    public Bitmap getIcon() {
        return mIcon;
    }
    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }
}