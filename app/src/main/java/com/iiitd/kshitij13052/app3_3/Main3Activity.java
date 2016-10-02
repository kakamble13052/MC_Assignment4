package com.iiitd.kshitij13052.app3_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    TextView t;
    ArrayList<String> s, u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent j = getIntent();
        s = j.getStringArrayListExtra("info");
        u = j.getStringArrayListExtra("info_2");
        setContentView(R.layout.activity_main3);
        t = (TextView) findViewById(R.id.textView);
        t.append("Name"+"       "+"Age\n\n");
        for (int i=0; i<s.size();i++){
            t.append(s.get(i)+"       "+u.get(i));
            t.append("\n");
        }
    }
}
