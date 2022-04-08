package com.example.android.AssigmentNoteApp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.AssigmentNoteApp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView mRegister;
    private TextView mButton;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        mRegister = findViewById(R.id.tv_register);
        mButton = findViewById(R.id.btn_login);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();

                if (inputEmail.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email id", Toast.LENGTH_SHORT).show();

                } else if (!inputEmail.matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();

                } else if (inputPassword.isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();

                } else if (inputPassword.length() < 8) {
                    Toast.makeText(LoginActivity.this, "Please enter 8 digit password", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

                }


            }
        });
    }
}


