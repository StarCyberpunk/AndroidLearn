package com.example.toolsshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrialDetailInter extends AppCompatActivity {
    private ImageView IdRes;
    private TextView title;
    private TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drial_detail_inter);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        title=findViewById(R.id.textViewTitelInter);
        info=findViewById(R.id.textViewInfoInter);
        IdRes=findViewById(R.id.imageViewInter);
        Intent intent=getIntent();
        title.setText(intent.getStringExtra("title"));
        info.setText(intent.getStringExtra("info"));
        IdRes.setImageResource(intent.getIntExtra("resID",0));

    }
}