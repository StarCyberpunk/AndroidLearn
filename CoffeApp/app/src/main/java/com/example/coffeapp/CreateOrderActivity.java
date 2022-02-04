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
    private String WantMore;
    private StringBuilder bulder;
    private TextView welcome;

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
        if(intent.hasExtra("name")&&intent.hasExtra("password")) {
            Name = intent.getStringExtra("name");
            Password = intent.getStringExtra("password");
        }
        else {
            Name="def";Password="def";
        }
         welcome=findViewById(R.id.textViewHiUser);
        WantMore=String.format("чай");
         /*tea=findViewById(R.id.radioButTea);
         coffe=findViewById(R.id.radioButCoffe);*/
         Dopoptc=findViewById(R.id.textViewTakeSpiner);
         Sahar=findViewById(R.id.checkBoxSahar);
         Milk=findViewById(R.id.checkBoxMilk);
         Limon=findViewById(R.id.checkBoxLimon);
         Coffespin=findViewById(R.id.spinnerCoffee);
         Teaspin=findViewById(R.id.spinnerTea);

        welcome.setText(String.format(getString(R.string.HelloClas),Name));
        bulder=new StringBuilder();

         /*if(tea.isChecked()){
             Dopoptc.setText("Что добавить в ваш чай?");


         }
         else if(coffe.isChecked()){
             Dopoptc.setText("Что добавить в ваш кофе?");

         }*/


        }

    public void choisedOrder(View view) {
        bulder.setLength(0);
        if(Milk.isChecked()){
            bulder.append("Молоко").append(" ");
        }
        if(Limon.isChecked()){
            bulder.append("Лимон").append(" ");
        }
        if(Sahar.isChecked()&&WantMore.equals(getString(R.string.ChaiRadio))){
            bulder.append("Сахар").append(" ");
        }
        String optionsDrink="";
         if(WantMore.equals("чай")){
             optionsDrink=Teaspin.getSelectedItem().toString();
         }
         else if(WantMore.equals("кофе")){
             optionsDrink=Coffespin.getSelectedItem().toString();
         }
        String order=String.format(getString(R.string.Order),Name,Password,WantMore,optionsDrink);
         String additions;
         if(bulder.length()>0){
             additions="Добавки:"+bulder.toString();

         }else {
             additions="";
         }
         String fullorder=order+additions;
         Intent intent = new Intent(this,OrderActivity.class);
         intent.putExtra("order",fullorder);
         startActivity(intent);
    }

    public void onClickChange(View view) {
        RadioButton button=(RadioButton) view;
        int id=button.getId();
        if(id==R.id.radioButTea){
            Dopoptc.setText(String.format("Что добавить в ваш %s ?",WantMore));
            Coffespin.setVisibility(View.INVISIBLE);
            Teaspin.setVisibility(View.VISIBLE);
            Limon.setVisibility(View.VISIBLE);
        }
        else if(id==R.id.radioButCoffe) {
            WantMore="кофе";
            Dopoptc.setText(String.format("Что добавить в ваш %s ?",WantMore));
            Teaspin.setVisibility(View.INVISIBLE);
            Coffespin.setVisibility(View.VISIBLE);
            Limon.setVisibility(View.INVISIBLE);

        }
    }
}