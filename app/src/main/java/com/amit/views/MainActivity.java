package com.amit.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import static com.amit.views.utils.Constants.KEY_USER_NAME;

/**
 * This class is used for Login functionality
 *
 * @author amit.bansode
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private String name = "";
    private String registeredName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * This function initiates the view
     */
    private void initViews() {
        etUserName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        tilUserName = findViewById(R.id.tilName);
        tilPassword = findViewById(R.id.tilPassword);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    /**
     * This method is for opening register activity
     */
    private void callRegister() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

//        Bundle bundle = new Bundle();
//        bundle.putString("NAME", name);
//        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * This function validates the views
     */
    private void validateEntries() {
        boolean isRegistered = false;

        String password = "";
        if (!etUserName.getText().toString().isEmpty()) {
            name = etUserName.getText().toString();
            tilUserName.setError(null);
        } else {
            tilUserName.setError(getString(R.string.user_name_error_message));
        }
        if (!etPassword.getText().toString().isEmpty()) {
            password = etPassword.getText().toString();
            tilPassword.setError(null);
        } else {
            tilPassword.setError(getString(R.string.user_password_error_message));
        }
        if (etUserName.getText().toString().equalsIgnoreCase(registeredName)) {
            isRegistered = true;
        } else {
            Toast.makeText(this, "please register", Toast.LENGTH_SHORT).show();
        }

        if (!etUserName.getText().toString().isEmpty() &&
                !etPassword.getText().toString().isEmpty() &&
                isRegistered) {
            Toast.makeText(MainActivity.this, getString(R.string.message_login_successful),
                    Toast.LENGTH_SHORT).show();
            callDashboard();
        }
    }

    private void callDashboard() {
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//        intent.putExtra(Constants.KEY_NAME, name);

        Bundle bundle = new Bundle();
        bundle.putString("NAME", name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                validateEntries();
                break;
            case R.id.btRegister:
                callRegister();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registeredName = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE).
                getString(KEY_USER_NAME, "");
    }
}