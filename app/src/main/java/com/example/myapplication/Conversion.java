package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;

public class Conversion extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        String type = getIntent().getStringExtra("type");
    }
}
