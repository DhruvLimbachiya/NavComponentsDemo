package com.example.navcomponentexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navcomponentexample.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpDrawerNavigation();
    }

    private void setUpDrawerNavigation() {

        // Get NavController
        final NavController navController = Navigation.findNavController(this,R.id.main_nav_host);

        final DrawerLayout drawerLayout = binding.mainDrawerLayout;

        // Config AppBar with DrawerLayout
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(drawerLayout)
                .build();

        // Navigation View
        NavigationView navigationView = binding.navView;

        NavigationUI.setupWithNavController(binding.toolbar,navController,appBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                
                int menuId = menuItem.getItemId();
                
                switch (menuId){
                    
                    case R.id.menu_dashboard:
                        navController.navigate(R.id.mainToDashboardFragment);
                        break;

                    case R.id.menu_profile:
                        navController.navigate(R.id.mainToProfileFragment);
                        break;

                    case R.id.menu_contact:
                        navController.navigate(R.id.mainToContactFragment);
                        break;

                    case R.id.menu_logout:
                        Toast.makeText(MainActivity.this, "Logout!", Toast.LENGTH_SHORT).show();
                        break;

                }
                
                return false;
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                drawerLayout.closeDrawers();
            }
        });

    }

}