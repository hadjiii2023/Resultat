package com.example.resultat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton add_button;
    Button alldata_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add_button = findViewById(R.id.add_button);
        alldata_button = findViewById(R.id.alldata_button);
        add_button.setOnClickListener(this);
        alldata_button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.add_button): {
                startActivity(new Intent(this, AddActivity.class));
                break;
            }
            case (R.id.alldata_button): {
                startActivity(new Intent(this, AllData.class));
                break;
            }
        }
    }
}
