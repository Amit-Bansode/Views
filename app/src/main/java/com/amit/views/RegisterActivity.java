package com.amit.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private Button btRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!etName.getText().toString().isEmpty()) {
            storeNameInPreference();
            etName.setError(null);
        } else {
            etName.setError("Please enter the name");
        }
    }

    /**
     * This method stores names in preference
     */
    private void storeNameInPreference() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", etName.getText().toString());
        editor.apply();

        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}