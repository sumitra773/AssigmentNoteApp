package com.example.android.AssigmentNoteApp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.AssigmentNoteApp.R;
public class RegisterActivity extends AppCompatActivity {

    private EditText name, mobile, email, password;

    private TextView mRegisterButton;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String namePattern = "[A-Za-z]{1,}";
    String mobilePattern = "[0-9]{10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        name = findViewById(R.id.et_name);
        mobile = findViewById(R.id.et_mobile);
        mRegisterButton = findViewById(R.id.btn_register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String inputName = name.getText().toString();
                String inputMobile = mobile.getText().toString();
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();


                if (inputName.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (!inputName.matches(namePattern)) {
                    Toast.makeText(RegisterActivity.this, "Please enter valid name", Toast.LENGTH_SHORT).show();
                } else if (inputMobile.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter mobile no", Toast.LENGTH_SHORT).show();
                } else if (!inputMobile.matches(mobilePattern)) {
                    Toast.makeText(RegisterActivity.this, "Please enter 10 digit mobile no", Toast.LENGTH_SHORT).show();

                } else if (inputEmail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter email id", Toast.LENGTH_SHORT).show();

                } else if (!inputEmail.matches(emailPattern)) {
                    Toast.makeText(RegisterActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();

                } else if (inputPassword.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();

                } else if (inputPassword.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Please enter 8 digit password", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}