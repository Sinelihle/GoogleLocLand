package com.example.googlelocland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void onRegisterClick(View view) {
        EditText email = findViewById(R.id.email);
        EditText uName = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);

        if(email.getText().toString().indexOf("@") < 1 || uName.getText().toString().length() < 4 || pass.getText().toString().length() < 8){
            Toast.makeText(this, "Please re-enter your credentials correctly", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, HomeMapActivity.class);
            startActivity(i);
        }
    }
    public void onRegisterLinkClick(View view){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
