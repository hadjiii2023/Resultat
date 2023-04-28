package com.example.resultat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button consult_button, idBtnLogin;
    DataBaseHelper myDB ;
    EditText req_cin,idEdtUserName,idEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idBtnLogin = findViewById(R.id.idBtnLogin);
        idEdtUserName = findViewById(R.id.idEdtUserName);
        idEdtPassword = findViewById(R.id.idEdtPassword);
        req_cin = findViewById(R.id.req_cin);
        consult_button = findViewById(R.id.consult_button);
        consult_button.setOnClickListener(this);
        idBtnLogin.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        String user = idEdtUserName.getText().toString();
        String password = idEdtPassword.getText().toString();
        String cin = req_cin.getText().toString();

        switch (v.getId()) {
            case (R.id.consult_button): {
                DataBaseHelper myDB = new DataBaseHelper(MainActivity.this );
                myDB.getMoyenne(cin) ;
                break;
            }

            case (R.id.idBtnLogin): {

                if(user.equals("admin") && password.equals("admin")) {
                    startActivity(new Intent(this, AdminActivity.class));
                                  }
                else {
                    Toast.makeText(this, "Incorrect Login et/ou Mot de passe", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            }
        }





}