package com.ist.onlinemovieapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ist.onlinemovieapplication.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();


        binding.createLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivty.class));

            }
        });

        binding.forgotPassBtn.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivty.class));

        });


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = Objects.requireNonNull(binding.email.getEditText()).getText().toString();
                String password = Objects.requireNonNull(binding.password.getEditText()).getText().toString();

                if (userEmail.isEmpty()) {
                    binding.email.setError("Name is required");
                    return;
                }
                if (password.isEmpty()) {
                    binding.password.setError("Age is required");
                    binding.email.setErrorEnabled(false);

                } else {
                    binding.password.setErrorEnabled(false);
                    userLogin(userEmail, password);
                }
            }
        });



    }


    private void userLogin(String userEmail,String userPassword){

        binding.progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                binding.progressBar.setVisibility(View.GONE);

                if(task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);

            }
        });
    }
}