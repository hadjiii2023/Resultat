package com.example.resultat;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText cin_input, moy_input;
    Spinner  res_input;
    Button update_button, delete_button;

    String id, cin, moy, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        cin_input = findViewById(R.id.cin_input2);
        moy_input = findViewById(R.id.moy_input2);
        res_input = findViewById(R.id.res_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DataBaseHelper myDB = new DataBaseHelper(UpdateActivity.this);
                myDB.deleteById(id);
            }
        });



        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DataBaseHelper myDB = new DataBaseHelper(UpdateActivity.this);
                cin = cin_input.getText().toString().trim();
                moy = moy_input.getText().toString().trim();
                result = res_input.getSelectedItem().toString().trim();
                myDB.UpdateData(id, cin, moy, result);
            }
        });


    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("cin") &&
                getIntent().hasExtra("moy") && getIntent().hasExtra("res")){
            //getting data
            id = getIntent().getStringExtra("id");
            cin = getIntent().getStringExtra("cin");
            moy = getIntent().getStringExtra("moy");
            result = getIntent().getStringExtra("res");

            //setting data
            cin_input.setText(cin);
            moy_input.setText(moy);
            //setting data set spinner value with item position
            for (int i=0;i<res_input.getCount();i++) {
                if (res_input.getItemAtPosition(i).toString().equalsIgnoreCase(result)) {
                    res_input.setSelection(i);
                }
            }
        }else{

            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
}
