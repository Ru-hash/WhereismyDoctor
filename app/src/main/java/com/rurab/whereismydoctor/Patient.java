package com.rurab.whereismydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Patient extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        Bundle b=getIntent().getExtras();
        String user=b.getString("user");

        Bundle b1= new Bundle();
        b1.putString("ur",user);
        i.putExtras(b1);
        startActivity(i);
    }
}
