package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name=findViewById(R.id.LoginTExt);
        Password=findViewById(R.id.PasswordText);
    }

    public void goToOrder(View view){
        String nname=Name.getText().toString().trim();//трим -избавление от пробела
        String password=Password.getText().toString().trim();
        if(!nname.isEmpty()&&!password.isEmpty()){
        Intent intent=new Intent(this,CreateOrderActivity.class);
        intent.putExtra("name",nname);
        intent.putExtra("password",password);
        startActivity(intent);
        }
        else {
            Toast.makeText(this,
                    R.string.ErrorEmty,
                    Toast.LENGTH_SHORT).show();
            //Всплывающее окно
        }
    }
}