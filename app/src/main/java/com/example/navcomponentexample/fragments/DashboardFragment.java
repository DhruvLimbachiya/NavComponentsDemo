package com.example.navcomponentexample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navcomponentexample.MainActivity;
import com.example.navcomponentexample.MainActivityArgs;
import com.example.navcomponentexample.R;
import com.example.navcomponentexample.databinding.FragmentDashboardBinding;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dashboard, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetching data which is recevied by MainActivity Arguments.
        if (MainActivity.args != null) {

            String username = MainActivity.args.getUsername();

            String welcomeMessage = "Welcome "+username ;
            // Display username in Dashboard screen.
            binding.dashboardUsername.setText(welcomeMessage);
        }
    }
}