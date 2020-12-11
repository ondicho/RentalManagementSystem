package com.moringa.rentalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Constants.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG=SignUpActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase RMS;
    DatabaseReference reference;

    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView(R.id.apartmentNumberEditText) EditText mApartmentNumberEditText;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmpasswordEditText;
    @BindView(R.id.signUpButton) Button mSignUpButton;
    @BindView(R.id.loginTextView) TextView mLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth=FirebaseAuth.getInstance();
        createAuthStateListener();

        mLoginTextView.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);
    }

    private void createNewUser() {

        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.toString().trim();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d(TAG,"Authentication successful");
                                } else{
                                    Toast.makeText(SignUpActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    private void createAuthStateListener(){
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(SignUpActivity.this,DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==mLoginTextView){
            Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        if(v==mSignUpButton){
           createNewUser();
           RMS=FirebaseDatabase.getInstance();
           reference=RMS.getReference(Constants.FIREBASE_CHILD_USERS);
            final String name = mNameEditText.getText().toString().trim();
            final String email = mEmailEditText.getText().toString().trim();
            String apartmentNumber = mApartmentNumberEditText.getText().toString().trim();

            User user=new User(name,email,apartmentNumber);
           reference.child(apartmentNumber).setValue(user);
        }
    }
}