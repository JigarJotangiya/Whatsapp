package com.example.whatsapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Database.Database;

import java.util.List;

public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentHolder> {

    Activity activity;
   public static List<StudentData> dataList;

    RecyclerView list;
    Database    database;
    StudentAdapter2 adapter;
    EditText i1;


    public StudentAdapter2(Activity activity , List<StudentData> dataList){

        this.activity=activity;
        this.dataList=dataList;
    }

    @NonNull
    @Override
    public StudentAdapter2.StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.list_item2,parent,false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText("NAME :"+dataList.get(position).name);
        holder.surname.setText("SURNAME :"+dataList.get(position).surname);
        holder.std.setText("STD :"+dataList.get(position).std);

        holder.D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = new Database(activity);
                database.DeleteData(dataList.get(position).id);
                MainActivity3.setdata(activity);
            }
        });

        holder.U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(activity);;

                dialog.setContentView(R.layout.update);

                TextView ids = dialog.findViewById(R.id.id);
                EditText name = dialog.findViewById(R.id.name);
                EditText surname = dialog.findViewById(R.id.surname);
                EditText std = dialog.findViewById(R.id.std);
                Button update = dialog.findViewById(R.id.update);

                ids.setText(""+dataList.get(position).id);
                name.setText(dataList.get(position).name);
                surname.setText(dataList.get(position).surname);
                std.setText(dataList.get(position).std);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        database = new Database(activity);
                        database.Update(dataList.get(position).id,name.getText().toString(),surname.getText().toString(),std.getText().toString());
                        dialog.dismiss();
                        MainActivity3.setdata(activity);
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() { return  dataList.size();}

    public class StudentHolder extends RecyclerView.ViewHolder {

        TextView name,surname,std;
        Button U,D;
        EditText i1;
        Database database;

        public StudentHolder(@NonNull View itemView){
            super(itemView);

            i1=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            surname=itemView.findViewById(R.id.surname);
            std=itemView.findViewById(R.id.std);

            U=itemView.findViewById(R.id.U);
            D=itemView.findViewById(R.id.D);

        }
    }
}
