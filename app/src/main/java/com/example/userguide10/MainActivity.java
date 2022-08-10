package com.example.userguide10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText userNameEdittext;
    private EditText passwordEdittext;
    private Button loginMain;
    private TextView register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEdittext=findViewById(R.id.userNameEditText);
        passwordEdittext=findViewById(R.id.passwordEditText);
        loginMain=findViewById(R.id.Main_login);
        register=findViewById(R.id.registerNewUser);
        auth=FirebaseAuth.getInstance();

        loginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEdittext.getText().toString();
                String passwordText = passwordEdittext.getText().toString();

                loginUser(userName,passwordText);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
    }

    private void loginUser(String name,String password){
        auth.signInWithEmailAndPassword(name,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,afterLoginActivity.class));
                finish();
            }
        });
    }
}