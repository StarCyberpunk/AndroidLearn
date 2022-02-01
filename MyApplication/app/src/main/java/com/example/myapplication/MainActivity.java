package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerColors;
    private TextView textViewColors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerColors =findViewById(R.id.spinnerColors);
        textViewColors=findViewById(R.id.textViewDescription);
    }

    public void showDescription(View view) {
        int position= spinnerColors.getSelectedItemPosition();
       String decription= getPositionElement(position);
       textViewColors.setText(decription);
    }
    private String getPositionElement(int position){
        String[] charter=getResources().getStringArray(R.array.descriptionTempColor);
        return charter[position];
    }
}