package com.moringa.rentalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import Constants.Constants;
import adapters.FirebasePaymentViewHolder;
import adapters.PaymentListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class RentStatusActivity extends AppCompatActivity {

    private static final String TAG=RentStatusActivity.class.getSimpleName();

    private DatabaseReference mPaymentReference;
    private FirebaseRecyclerAdapter<Payment,FirebasePaymentViewHolder>mFirebaseAdapter;
    private ValueEventListener referenceListener;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private PaymentListAdapter mAdapter;

    public List<Payment> payments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_status);
        ButterKnife.bind(this);

        mPaymentReference=FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PAYMENTS);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Payment> options=new FirebaseRecyclerOptions.Builder<Payment>()
                .setQuery(mPaymentReference,Payment.class)
                .build();

        mFirebaseAdapter=new FirebaseRecyclerAdapter<Payment, FirebasePaymentViewHolder>(options){

            @NonNull
            @Override
            public FirebasePaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item,parent,false);
              return new FirebasePaymentViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FirebasePaymentViewHolder firebasePaymentViewHolder, int i, @NonNull Payment payment) {
                firebasePaymentViewHolder.bindPayment(payment);
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
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}