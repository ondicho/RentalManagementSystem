package com.moringa.rentalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import Constants.Constants;
import adapters.PaymentListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class RentStatusActivity extends AppCompatActivity {
    FirebaseDatabase RMS=FirebaseDatabase.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PAYMENTS);

    @BindView(R.id.apartmentNumTextView) TextView mApartmentNumTextView;
    @BindView(R.id.monthTextView) TextView mMonthTextView;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private PaymentListAdapter mAdapter;

    public List<Payment> invoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_status);
        ButterKnife.bind(this);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Payment invoice=snapshot.getValue(Payment.class);
                String apartmentNumber=invoice.getApartmentNo();
                String month=invoice.getMonth();

                mApartmentNumTextView.setText(apartmentNumber);
                mMonthTextView.setText(month);
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

            }
        });

        mAdapter=new PaymentListAdapter(RentStatusActivity.this,invoice);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(RentStatusActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}