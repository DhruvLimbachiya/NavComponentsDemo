package com.example.navcomponentexample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navcomponentexample.R;
import com.example.navcomponentexample.databinding.FragmentLoginBinding;
import com.example.navcomponentexample.pojo.User;

import static com.example.navcomponentexample.fragments.LoginFragmentDirections.*;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    NavController navController;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);

        user = new User();
        binding.setUser(user);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        view.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    logIn();
                }
            }
        });


        view.findViewById(R.id.reg_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToReg();
            }
        });
    }

    /**
     * Navigate to Register Fragment
     */
    private void navToReg() {

        // Navigate to Registration screen using SafeArgs
        NavDirections regAction = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment();

        navController.navigate(regAction);
    }

    /**
     * Log in to the app and display MainActivity
     */
    private void logIn() {

        user = binding.getUser();

        // Navigate to MainActivity with successful login using SafeArgs.
        ActionLoginFragmentToMainActivity action = LoginFragmentDirections.actionLoginFragmentToMainActivity();
        action.setUsername(user.getName()); // Transferring "username" using SafeArgs

        navController.navigate(action);
    }

    /**
     * Validate users input (Credentials)
     * @return - True if valid or False if invalid
     */
    private boolean isValid() {
        TextView username = binding.usernameTextview;
        TextView password = binding.passwordTextview;

        if(TextUtils.isEmpty(username.getText().toString().trim())){
            binding.usernameTextview.setEnabled(true);
            binding.usernameTextview.setError(getString(R.string.error_username));
            return false;
        }else if(TextUtils.isEmpty(password.getText().toString().trim())){
            binding.passwordTextview.setEnabled(true);
            binding.passwordTextview.setError(getString(R.string.error_password));
            return false;
        }
        return true;
    }
}