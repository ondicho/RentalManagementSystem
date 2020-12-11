package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Constants.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class ConfirmPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private ValueEventListener mPaymentsReferenceListener;
    private DatabaseReference mPaymentsReference;

    @BindView(R.id.apartmentNumberEditText) EditText mApartmentNumberEditText;
    @BindView(R.id.monthEditText) EditText mMonthEditText;
    @BindView(R.id.transactionCodeEditText) EditText mTransactionCodeEditText;
    @BindView(R.id.amountEditText) EditText mAmountEditText;
    @BindView(R.id.confirmPaymentButton) Button mConfirmPaymentButton;

    private Payment mPayments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    mPaymentsReference=FirebaseDatabase .getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCHED_PAYMENTS);
//    mPaymentsReferenceListener=mPaymentsReference.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        ButterKnife.bind(this);
        mConfirmPaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mConfirmPaymentButton){
            DatabaseReference paymentsRef= FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PAYMENTS);
            paymentsRef.push().setValue((mPayments));
//            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}