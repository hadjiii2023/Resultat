package com.example.resultat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllData extends AppCompatActivity  {
    RecyclerView recyclerView;
    DataBaseHelper myDB;
    ArrayList<String> id, cin, moy, result;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldata);
        recyclerView = findViewById(R.id.recyclerView);
        myDB = new DataBaseHelper(AllData.this);
        id = new ArrayList<>();
        moy = new ArrayList<>();
        result = new ArrayList<>();
        cin = new ArrayList<>();

        StoreDataArray();
        customAdapter = new CustomAdapter(AllData.this, this, id, cin, moy, result);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllData.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void StoreDataArray() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                cin.add(cursor.getString(1));
                moy.add(cursor.getString(2));
                result.add(cursor.getString(3));
            }
        }
    }
}