package com.moringa.rentalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Constants.Constants;
import adapters.FirebasePaymentViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class SavedPaymentActivity extends AppCompatActivity {

    private DatabaseReference mPaymentReference;
    private FirebaseRecyclerAdapter<Payment, FirebasePaymentViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_status);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Uid = user.getUid();

        mPaymentReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PAYMENTS);
        FirebaseRecyclerOptions<Payment> options = new FirebaseRecyclerOptions.Builder<Payment>()
                .setQuery(mPaymentReference, Payment.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Payment, FirebasePaymentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePaymentViewHolder firebasePaymentViewHolder, int i, @NonNull Payment payment) {
                firebasePaymentViewHolder.bindPayment(payment);
            }

            @NonNull
            @Override
            public FirebasePaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item, parent, false);
                return new FirebasePaymentViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }
}