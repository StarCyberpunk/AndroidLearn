package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private String Name;
    private String Password;
    private TextView welcome;
    private RadioButton tea;
    private RadioButton coffe;
    private TextView Dopoptc;
    private CheckBox Sahar;
    private CheckBox Milk;
    private CheckBox Limon;
    private Spinner Teaspin;
    private Spinner Coffespin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent=getIntent();
        Name=intent.getStringExtra("name");
        Password=intent.getStringExtra("password");

         welcome=findViewById(R.id.textViewHiUser);

         tea=findViewById(R.id.radioButTea);
         coffe=findViewById(R.id.radioButCoffe);
         Dopoptc=findViewById(R.id.textViewTakeSpiner);
         Sahar=findViewById(R.id.checkBoxSahar);
         Milk=findViewById(R.id.checkBoxMilk);
         Limon=findViewById(R.id.checkBoxLimon);
         Coffespin=findViewById(R.id.spinnerCoffee);
         Teaspin=findViewById(R.id.spinnerTea);

        welcome.setText("Привет "+Name+",что будете заказывать?");

         if(tea.isChecked()){
             Dopoptc.setText("Что добавить в ваш чай?");


         }
         else if(coffe.isChecked()){
             Dopoptc.setText("Что добавить в ваш кофе?");

         }


        }

    public void choisedOrder(View view) {
        if (Milk.isChecked()){}
        if (Limon.isChecked()){}
        if (Sahar.isChecked()){}
    }
}