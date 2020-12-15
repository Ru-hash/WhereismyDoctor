package com.rurab.whereismydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class patres2 extends AppCompatActivity {
 TextView t;

    public void book(View t) {

        Intent i=new Intent(getApplicationContext(),MainActivity.class);

        String user="pat2";

        Bundle b1= new Bundle();
        b1.putString("ur",user);
        i.putExtras(b1);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patres2);
        t=findViewById(R.id.t);
        t.setMovementMethod(new ScrollingMovementMethod());
        Bundle b=getIntent().getExtras();
        String rev=b.getString("key");
        Toast.makeText(this, "Note down the Doctor ID for booking ", Toast.LENGTH_SHORT).show();

        t.setText(rev);
    }
}
