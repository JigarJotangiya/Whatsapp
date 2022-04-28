package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.whatsapp.Database.Database;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    public static Database database;
    Button insert;
    EditText name,surname,std;
    public static RecyclerView list;
    TextView t1,t2,t3;
    public static StudentAdapter2 adapter;

    private static final String TAG ="MainActivity3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        std=findViewById(R.id.std);

        list=findViewById(R.id.list);
        database=new Database(MainActivity3.this);
        insert=findViewById(R.id.insert);

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.surname);
        t3=findViewById(R.id.std);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n1 = name.getText().toString();
                String n2 = surname.getText().toString();
                String n3 = std.getText().toString();

                database.Insertdata(n1,n2,n3);

                List<StudentData> datalist = database.RetriveData();
                adapter = new StudentAdapter2(MainActivity3.this,datalist);
                list.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
                list.setAdapter(adapter);

            }
        });

    }

    public static void setdata(Activity activity) {
        List<StudentData> datalist = database.RetriveData();
        adapter = new StudentAdapter2(activity,datalist);
        list.setLayoutManager(new LinearLayoutManager(activity));
        list.setAdapter(adapter);
    }

}