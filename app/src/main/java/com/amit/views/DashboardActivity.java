package com.amit.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amit.views.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private String name = "";
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString(Constants.KEY_NAME);
//        name = getIntent().getExtras().getString("NAME");

        tvName = findViewById(R.id.tvName);
        try {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aaa", Locale.getDefault());
                String CurrentTime = dateFormat.format(Calendar.getInstance().getTime());

                tvName.setText(name + "                  " + CurrentTime);


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}