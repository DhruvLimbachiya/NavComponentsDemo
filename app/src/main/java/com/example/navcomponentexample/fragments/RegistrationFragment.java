package com.example.navcomponentexample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navcomponentexample.R;
import com.example.navcomponentexample.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_registration, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        view.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    regAndNavToLogin();
                }
            }
        });
    }

    private boolean isValid() {

        TextView username = binding.nameTextview;
        TextView email = binding.emailTextview;
        TextView password = binding.passwordTextview;

        if(TextUtils.isEmpty(username.getText().toString().trim())){
            binding.nameTextview.setEnabled(true);
            binding.nameTextview.setError(getString(R.string.error_username));
            return false;
        }else if(TextUtils.isEmpty(email.getText().toString().trim())){
            binding.emailTextview.setEnabled(true);
            binding.emailTextview.setError(getString(R.string.error_email));
            return false;
        }else if(TextUtils.isEmpty(password.getText().toString().trim())){
            binding.passwordTextview.setEnabled(true);
            binding.passwordTextview.setError(getString(R.string.error_password));
            return false;
        }

        return true;
    }

    /**
     * Register user and navigate to Login Screen
     */
    private void regAndNavToLogin() {
        NavDirections navDirections = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment();
        navController.navigate(navDirections);
    }
}