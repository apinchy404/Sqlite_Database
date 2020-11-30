package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etId,etName,etEmail;
    Button btnSave,btnView,btnUpdate,btnDelete;
    
    DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId=findViewById(R.id.et_id);
        etName=findViewById(R.id.et_name);
        etEmail=findViewById(R.id.et_email);

        btnSave=findViewById(R.id.btn_save);
        btnView=findViewById(R.id.btn_view);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete=findViewById(R.id.btn_delete);
        
        dbHelper=new DBhelper(MainActivity.this);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etId.getText().toString();
                String name=etName.getText().toString();
                String email=etEmail.getText().toString();

                if (id.isEmpty())
                {
                    etId.setError("Please input your id");
                    etId.requestFocus();
                }
                
                else if(name.isEmpty())
                {
                    etName.setError("Please input your name");
                    etName.requestFocus();
                }
                else if(email.isEmpty()|| !email.contains("@")|| !email.contains("."))
                {
                    etEmail.setError("Please input valid email");
                    etEmail.requestFocus();
                }
                else
                {
                    boolean check =dbHelper.insertData(id,name,email);
                    if(check==true)
                    {
                        Toast.makeText(MainActivity.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Data insert fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);

            }
        });
    }
}