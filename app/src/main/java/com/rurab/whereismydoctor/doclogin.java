package com.rurab.whereismydoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class doclogin extends AppCompatActivity {
    EditText pass,id;
    public void login(View v){
        pass=findViewById(R.id.editText8);
        id=findViewById(R.id.editText7);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doclogin);
    }
}
