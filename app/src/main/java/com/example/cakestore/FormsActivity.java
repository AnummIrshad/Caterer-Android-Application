package com.example.cakestore;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class FormsActivity extends AppCompatActivity {

    private EditText dateEditText;
    private Calendar calendar;

    private static final int PICK_CONTACT_REQUEST = 1;
    private static final int CONTACT_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formsactivity);

        // Initialize the date EditText and Calendar instance
        dateEditText = findViewById(R.id.dateEditText);
        calendar = Calendar.getInstance();

        // Set OnClickListener for the date EditText
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show DatePickerDialog when the EditText is clicked
                showDatePickerDialog();
            }
        });

        // Find the Enquire button by its ID
        Button enquireButton = findViewById(R.id.enquireButton);

        // Set OnClickListener to the Enquire button
        enquireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the GeoLocationActivity
                Intent intent = new Intent(FormsActivity.this, GoogleMapsActivity.class);
                startActivity(intent); // Start the GeoLocationActivity
            }
        });
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the date EditText with the selected date
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, dayOfMonth);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    public void openContacts(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_REQUEST_CODE);
        } else {
            // Permission has already been granted, start the contacts picker
            startContactsPicker();
        }
    }

    private void startContactsPicker() {
        // Intent to pick contacts
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            // Handle the contact selection here
            // For example, you can retrieve the contact's name and display it in a TextView
            String contactName = retrieveContactName(data.getData());
            // Display the contact name in a TextView or EditText
            EditText contactEditText = findViewById(R.id.contact);
            contactEditText.setText(contactName);
        }
    }

    // Method to retrieve contact name from the contact URI
    private String retrieveContactName(Uri contactUri) {
        String name = null;
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(contactUri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                if (displayNameIndex != -1) {
                    name = cursor.getString(displayNameIndex);
                } else {
                    // Handle case when DISPLAY_NAME column is not found
                    // You can log an error message or provide a default value
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return name;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACT_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the contacts picker
                startContactsPicker();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied to access contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
