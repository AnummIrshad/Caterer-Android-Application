package com.example.cakestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GoogleMapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemapsactivity);

        // Find the confirm address button by its ID
        Button confirmAddressButton = findViewById(R.id.order_success);

        // Set OnClickListener to the button
        confirmAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to navigate to OrderSuccessActivity
                Intent intent = new Intent(GoogleMapsActivity.this, OrderSuccessActivity.class);
                startActivity(intent); // Start the new activity
            }
        });
    }
}
