package com.example.cakestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoriesactivity); // Assuming your categories layout file is categories_activity.xml

        Button menuButton = findViewById(R.id.menuButton); // Assuming you have a button with id menuButton
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to navigate to MenuActivity
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                startActivity(intent); // Start the new activity
            }
        });

        // Any additional initialization or logic can be added here
    }
}