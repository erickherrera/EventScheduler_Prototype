package com.example.eventscheduler01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventscheduler01.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DBHelper dbHelper;
    private SessionManager sessionManager; // You would need to create this class to manage session.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelper(this);
        sessionManager = new SessionManager(this);

        // Check if user is already logged in
        if (sessionManager.isLoggedIn()) {
            navigateToHome();
            return;
        }

        binding.loginButton.setOnClickListener(view -> attemptLogin());
    }

    private void attemptLogin() {
        String username = binding.loginUsername.getText().toString();
        String password = binding.loginPassword.getText().toString();

        boolean isLoggedIn = dbHelper.confirmUser(username, password);
        if (isLoggedIn) {
            sessionManager.setLogin(true);  // Set login status in session manager
            navigateToHome();
        } else {
            Toast.makeText(this, "Log in failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();  // Close login activity once logged in
    }
}
