package com.example.toolsshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DrealKategoryActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Drill> drills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreal_kategory);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        drills=new ArrayList<>();
        drills.add(new Drill(getString(R.string.interskolo),getString(R.string.interskolo_info),R.drawable.drill_main));
        drills.add(new Drill(getString(R.string.makita),getString(R.string.makita),R.drawable.makita));
        drills.add(new Drill(getString(R.string.dewalt),getString(R.string.dewalt_info),R.drawable.dewalt));
        listView=findViewById(R.id.ListViewDrils);
        ArrayAdapter<Drill> adapter=new ArrayAdapter<Drill>(getApplicationContext(), android.R.layout.simple_list_item_1,drills);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Drill drill=drills.get(i);
                Intent intent =new Intent(getApplicationContext(),DrialDetailInter.class);
                intent.putExtra("title",drill.getTitle());
                intent.putExtra("info",drill.getInfo());
                intent.putExtra("resID",drill.getResourseId());
                startActivity(intent);

            }
        });

    }
}