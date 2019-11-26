package com.example.litewifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        try {
            final Intent intent = getIntent();
            String res = getIntent().getStringExtra("res1");

            TextView RES =(TextView) findViewById(R.id.textView);
            if(!res.isEmpty())
                RES.setText(res);
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
