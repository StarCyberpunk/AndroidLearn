package com.example.strangecalcul;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SeekBar seekbar;
    private ArrayList<Integer> numbers;
    private int max=20;
    private int min=1;
    private int count =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.ListViewNumbers);
        seekbar=findViewById(R.id.seekBar);
        seekbar.setMax(max);

        numbers=new ArrayList<Integer>();
        final ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,numbers);
        listView.setAdapter(adapter);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numbers.clear();
                if(i<min){
                    seekbar.setProgress(min);

                }
                else {
                for (int j=min;j<=max;j++){
                    numbers.add(seekbar.getProgress()*j);
                }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar.setProgress(10);
    }
}