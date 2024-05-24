package com.example.cakestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu); //  menu layout file is menu.xml

        // Find the "Place order now" button by its ID
        Button placeOrderButton = findViewById(R.id.button5);

        // Set OnClickListener to the button
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to navigate to FormsActivity
                Intent intent = new Intent(MenuActivity.this, FormsActivity.class);
                startActivity(intent); // Start the new activity
            }
        });
    }
}
