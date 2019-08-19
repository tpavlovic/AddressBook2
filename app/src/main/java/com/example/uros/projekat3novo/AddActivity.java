package com.example.uros.projekat3novo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AddActivity extends AppCompatActivity {


    Context context = this;
    public DatabaseHelper helper  = new DatabaseHelper(context);
    public TextView addName, addNumber;
    public  String ime, telefon;
    SQLiteDatabase db;

    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter recyclerAdapter;
    private List<Contact> listContact;

    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        addName = (EditText) findViewById(R.id.nameSave);
        addNumber = (EditText) findViewById(R.id.numberSave);
        saveBtn = (Button)findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                ime = addName.getText().toString();
                telefon = addNumber.getText().toString();

                Contact c = new Contact(ime, telefon);
                addInTable(c);

                startActivity(intent);
                //finish();
            }
        });

    }

    public void addInTable(Contact c){
        try {
            helper.insertContact(c);;
            helper.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
