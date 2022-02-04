package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private TextView viewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        viewOrder=findViewById(R.id.textViewOrder);
        Intent intent=getIntent();
        if(intent.hasExtra("order")){
            String n=intent.getStringExtra("order");
            viewOrder.setText(n);
        }
        else {
            Intent intent1=new Intent(this,CreateOrderActivity.class);
            startActivity(intent1);
        }


    }

}