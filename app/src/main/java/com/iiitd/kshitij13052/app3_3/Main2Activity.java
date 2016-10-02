package com.iiitd.kshitij13052.app3_3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Context context = App3_3.getContext();
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    TextView name;
    TextView age;
    private DBHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mydb = new DBHelper(this);

        Button button = (Button) findViewById(R.id.button2);
        name = (TextView) findViewById(R.id.nameField);
        age = (TextView) findViewById(R.id.ageField);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                if (mydb.insertInfo(name.getText().toString(), age.getText().toString())) {
                    File file = Environment.getExternalStorageDirectory().getAbsoluteFile();
                    File f = new File(file, "info.txt");
                    if (!file.exists()) {
                        try {
                            if(!f.createNewFile())
                            {
                                throw new IOException();
                            }
                            else
                            {
                                PrintWriter g = new PrintWriter(f);
                                g.print(name.getText().toString() + "    " + age.getText().toString() + "\n");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            PrintWriter g = new PrintWriter(f);
                            g.print(name.getText().toString() + "    " + age.getText().toString()+"\n");
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    ArrayList<String> s = mydb.getAllNames();
                    ArrayList<String> u = mydb.getAllAges();
                    intent.putStringArrayListExtra("info", s);
                    intent.putStringArrayListExtra("info_2", u);
                    Toast t = Toast.makeText(context, "Save Successfull", Toast.LENGTH_SHORT);
                    t.show();
                    startActivity(intent);
                }
                else
                {
                    Toast t = Toast.makeText(context, "Save Failed", Toast.LENGTH_SHORT);
                    t.show();
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);

                }
                else
                {
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
