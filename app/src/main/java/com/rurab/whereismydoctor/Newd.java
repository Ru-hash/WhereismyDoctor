package com.rurab.whereismydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Newd extends AppCompatActivity {
    public void opens(View v){
        try {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            Bundle b=getIntent().getExtras();
            String user=b.getString("user");
            Bundle b1= new Bundle();
            b1.putString("ur",user);
            i.putExtras(b1);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void openl(View v){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);

        Bundle b1= new Bundle();
        b1.putString("ur","doc2");
        i.putExtras(b1);
        startActivity(i);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }
}
