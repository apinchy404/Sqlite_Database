package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(DetailsActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        DBhelper dBhelper=new DBhelper(DetailsActivity.this);


//        get data from db;
        List<HashMap<String,String>> data=dBhelper.getData();
        if(data.size()==0)
        {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Adapter adapter=new Adapter(DetailsActivity.this,data);
            recyclerView.setAdapter(adapter);
        }



    }


}