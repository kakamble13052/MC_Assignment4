package com.iiitd.kshitij13052.app3_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iiitd.kshitij13052.app3_3.App3_3;
import com.iiitd.kshitij13052.app3_3.DBHelper;
import com.iiitd.kshitij13052.app3_3.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> s, u;
    private DBHelper mydb = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            Intent i = getIntent();
            s = i.getStringArrayListExtra("info");
            u = i.getStringArrayListExtra("info_2");
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.DisplayData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putStringArrayListExtra("info", s);
                intent.putStringArrayListExtra("info_2", u);
                startActivity(intent);
            }
        });

        Button button_2 = (Button) findViewById(R.id.DeleteData);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mydb.deleteInfo())
                {
                    s = mydb.getAllNames();
                    u = mydb.getAllAges();
                    Toast.makeText( App3_3.getContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText( App3_3.getContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void add_data(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
