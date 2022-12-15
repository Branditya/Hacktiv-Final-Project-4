package id.branditya.hacktivfinalproject4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.branditya.hacktivfinalproject4.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnLogin = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}