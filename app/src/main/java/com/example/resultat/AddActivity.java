package com.example.resultat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {
    EditText moy_cin , moy_moy ;
    Spinner moy_result ;
    Button add_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        moy_cin = findViewById(R.id.moy_cin_txt);
        moy_moy = findViewById(R.id.moy_moy_txt);
        moy_result = findViewById(R.id.moy_res_txt);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(view -> {
            DataBaseHelper myDB = new DataBaseHelper(AddActivity.this );
            myDB.addMoyen(moy_cin.getText().toString().trim(),
                    moy_moy.getText().toString().trim() ,
                    moy_result.getSelectedItem().toString().trim()
            );
        });
    }
}
