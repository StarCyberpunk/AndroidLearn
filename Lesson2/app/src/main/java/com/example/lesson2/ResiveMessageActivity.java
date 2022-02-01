package com.example.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResiveMessageActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resive_message);
        Intent intent=getIntent();
       String message= intent.getStringExtra("Message");
       textView=findViewById(R.id.textViewResivedMessage);
       textView.setText(message);

    }


}