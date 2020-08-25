package com.example.navcomponentexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.navcomponentexample.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    public static MainActivityArgs args;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_text_dashboard));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpDrawerNavigation();

        // Retrieve the data sent by LoginFragment from MainActivityArgs Bundle.
        args = MainActivityArgs.fromBundle(Objects.requireNonNull(getIntent().getExtras()));

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
                        // Display Alert DailogBox on Logout Menu Clicked.
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                .setMessage(R.string.logout_message)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        onBackPressed();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
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