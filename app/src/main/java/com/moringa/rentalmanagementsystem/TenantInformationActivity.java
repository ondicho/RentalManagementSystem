package com.moringa.rentalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Constants.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.User;

public class TenantInformationActivity extends AppCompatActivity {

    FirebaseDatabase RMS=FirebaseDatabase.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_USERS);

    @BindView(R.id.tenantNameTextView)
    TextView tenantNameTextView;
    @BindView(R.id.apartmentNumTextView)
    TextView apartmentNumberTextView;
    @BindView(R.id.emailTextView)
    TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_information);
        ButterKnife.bind(this);

        reference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            User user=snapshot.getValue(User.class);
            String tenantName=user.getTenantName();
            String apartmentNumber=user.getApartmentNumber();
            String email=user.getEmail();

            tenantNameTextView.setText(tenantName);
            apartmentNumberTextView.setText(apartmentNumber);
            emailTextView.setText(email);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TenantInformationActivity.this,"Information Not Available,Try Later",Toast.LENGTH_SHORT).show();
            }
        });

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//
//                String tenantName = user.getTenantId();
//                tenantNameTextView.setText(tenantName);
//                String apartmentNumber = user.getUid();
//                apartmentNumberTextView.setText(apartmentNumber);
//                for (UserInfo profile : user.getProviderData()) {
//                String email = profile.getEmail();
//                emailTextView.setText(email);
//            }
//        }
    }
}