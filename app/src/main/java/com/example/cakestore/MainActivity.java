package com.example.cakestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    TextView SignupText; // Declaration for the Sign Up text view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        SignupText = findViewById(R.id.SignupText); // Initialize SignupText

        SignupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUpActivity when the link is clicked
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLoginButtonClick(View view) {
        // Perform login authentication
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        // Check if username or password is empty
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Username or password cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Assuming login is successful
        if (usernameText.equals("anumm@gmail.com") && passwordText.equals("1234")) {
            // Redirect to CategoriesActivity
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);

            // Finish the current activity to prevent the user from navigating back to the login page
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
        }
    }
}
