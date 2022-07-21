package com.ist.onlinemovieapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ist.onlinemovieapplication.databinding.ActivityRegisterBinding;
import com.ist.onlinemovieapplication.model.UserModel;

import java.util.Objects;

public class RegisterActivty extends AppCompatActivity {


    ActivityRegisterBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();



        binding.loginLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivty.this, LoginActivity.class));

            }
        });


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = Objects.requireNonNull(binding.username.getEditText()).getText().toString();
                String userAge = Objects.requireNonNull(binding.userAge.getEditText()).getText().toString();
                String userPhone = Objects.requireNonNull(binding.mobileNumber.getEditText()).getText().toString();
                String userEmail = Objects.requireNonNull(binding.email.getEditText()).getText().toString();
                String userPassword = Objects.requireNonNull(binding.password.getEditText()).getText().toString();

                String countyCode = binding.ccp.getSelectedCountryCode();
                Log.d("registerBtn", countyCode);
                if (userName.isEmpty()) {
                    binding.username.setError("Name is required");
                    return;
                }
                if (userAge.isEmpty()) {
                    binding.userAge.setError("Age is required");
                    binding.username.setErrorEnabled(false);
                    return;
                }
                if (userEmail.isEmpty()) {
                    binding.email.setError("Email is required");
                    binding.userAge.setErrorEnabled(false);

                    return;
                }
                if (userPhone.isEmpty()) {

                    binding.mobileNumber.setError("Number is required");

                    binding.email.setErrorEnabled(false);

                    return;
                }
                if (userPassword.isEmpty()) {
                    binding.password.setError("Password is required");
                    binding.mobileNumber.setErrorEnabled(false);
                } else {
                    binding.password.setErrorEnabled(false);
                    userRegister(userName, userAge, userEmail, userPhone, userPassword);
                }
            }
        });



    }

    private void userRegister(String userName, String userAge, String userEmail, String userPhone, String userPassword) {

        final UserModel user = new UserModel(userName, userAge, userEmail, userPhone,userPassword);

        binding.progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String uid = Objects.requireNonNull(task.getResult()).getUser().getUid();

                    database.collection("users")
                            .document(uid)
                            .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                binding.progressBar.setVisibility(View.GONE);

                                startActivity(new Intent(RegisterActivty.this, MainActivity.class));
                                finish();
                            } else {
                                binding.progressBar.setVisibility(View.GONE);

                                Log.d("ERROR",task.getException().getLocalizedMessage());
                                Toast.makeText(RegisterActivty.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    binding.progressBar.setVisibility(View.GONE);

                    Log.d("ERROR",task.getException().getLocalizedMessage());
                    Toast.makeText(RegisterActivty.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}