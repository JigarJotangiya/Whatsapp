package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.whatsapp.Database.Database;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Database database;
    Button insert,update,delete;
    EditText ids,name,surname,std;
    RecyclerView list;
    TextView t1,t2,t3;
    StudentAdapter adapter;

    private static final String TAG ="MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ids=findViewById(R.id.id);
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        std=findViewById(R.id.std);

        list=findViewById(R.id.list);
        database=new Database(MainActivity2.this);
        insert=findViewById(R.id.insert);

        update=findViewById(R.id.update);
        delete = findViewById(R.id.delete);

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
                adapter = new StudentAdapter(MainActivity2.this,datalist);
                list.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                list.setAdapter(adapter);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(ids.getText().toString());

                String n1 = name.getText().toString();
                String n2 = surname.getText().toString();
                String n3 = std.getText().toString();

                database.Update(id,n1,n2,n3);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(ids.getText().toString());
                database.DeleteData(id);

            }
        });
    }
}