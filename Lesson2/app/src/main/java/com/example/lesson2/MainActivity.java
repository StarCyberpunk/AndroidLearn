package com.example.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText message;
    private Activity TimerTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message=findViewById(R.id.editTextMessage);

    }

    public void SendMessage(View view) {
        String messa=message.getText().toString();
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,messa);
        Intent chaoseIntent=Intent.createChooser(intent,"hooo?");
        startActivity(chaoseIntent);

    }

    public void GoTimer(View view) {
        Intent intent=new Intent(this, TimerTutorial.class);//переход из этого в туториал
        startActivity(intent);
    }
}