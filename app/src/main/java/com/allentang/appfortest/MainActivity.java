package com.allentang.appfortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmail = (TextView) findViewById(R.id.textEmail);

        Bundle extras = getIntent().getExtras();
        String email;

        if(extras != null){
            email = extras.getString("Email");
            textEmail.setText("Welcome " + email);
        }

    }
}
