package com.rurab.whereismydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Opening extends AppCompatActivity {
    String user;
    public void opend(View v){
        try {
            Intent i=new Intent(getApplicationContext(),Newd.class);
            Bundle b=new Bundle();
            user="doc";
            b.putString("user",user);
            i.putExtras(b);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openp(View v){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);

        user="pat";
        Bundle b= new Bundle();
        b.putString("ur",user);
        i.putExtras(b);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
    }
}
