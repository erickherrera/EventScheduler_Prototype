package com.example.eventscheduler01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eventscheduler01.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureEdgeToEdgeExperience();
        setupNavigation();
    }

    private void configureEdgeToEdgeExperience() {
        // Enable edge-to-edge display mode where appropriate
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.setAppearanceLightStatusBars(true);

        // Apply system window insets as padding for the 'main' view
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void setupNavigation() {
        // Configuration for AppBar with IDs of top-level destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_settings).build();

        // Setup NavController for the bottom navigation view
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Support for navigating up within the app's navigation hierarchy
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp();
    }
}
