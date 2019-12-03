package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button length = findViewById(R.id.length);
        length.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "length");
                view.getContext().startActivity(intent);
            }
        });
        Button volume = findViewById(R.id.volume);
        volume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "volume");
                view.getContext().startActivity(intent);
            }
        });
        Button mass = findViewById(R.id.mass);
        mass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "mass");
                view.getContext().startActivity(intent);
            }
        });
        Button temp = findViewById(R.id.temp);
        temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "temp");
                view.getContext().startActivity(intent);
            }
        });
        Button time = findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "time");
                view.getContext().startActivity(intent);
            }
        });
        Button speed = findViewById(R.id.speed);
        speed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Conversion.class);
                intent.putExtra("type", "speed");
                view.getContext().startActivity(intent);
            }
        });
    }
}
