package com.example.ex9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class credits extends AppCompatActivity {
    Intent gi;
    double result;
    TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv = (TextView) findViewById(R.id.tv);
        gi = getIntent();
        result = gi.getDoubleExtra("sum", 0.0);
        tv.setText("" + result);}

    public void go(View view) {
        finish();
    }
}