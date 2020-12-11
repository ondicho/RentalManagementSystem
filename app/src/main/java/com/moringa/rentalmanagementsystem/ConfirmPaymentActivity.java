package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Constants.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;
import models.User;

public class ConfirmPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase RMS;
    DatabaseReference reference;
    private ValueEventListener mPaymentsReferenceListener;
    private DatabaseReference mPaymentsReference;

    @BindView(R.id.apartmentNumberEditText) EditText mApartmentNumberEditText;
    @BindView(R.id.monthEditText) EditText mMonthEditText;
    @BindView(R.id.transactionCodeEditText) EditText mTransactionCodeEditText;
    @BindView(R.id.amountEditText) EditText mAmountEditText;
    @BindView(R.id.confirmPaymentButton) Button mConfirmPaymentButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
    mPaymentsReference=FirebaseDatabase .getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCHED_PAYMENTS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        ButterKnife.bind(this);
        mConfirmPaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mConfirmPaymentButton){
            RMS=FirebaseDatabase.getInstance();
            reference=RMS.getReference(Constants.FIREBASE_CHILD_PAYMENTS);
            final String apartmentNumber = mApartmentNumberEditText.getText().toString().trim();
            final String month = mMonthEditText.getText().toString().trim();
            final String transactionCode = mTransactionCodeEditText.getText().toString().trim();
            final Integer amount= Integer.valueOf(mAmountEditText.getText().toString());

            Payment payment=new Payment(apartmentNumber,month,transactionCode,amount);
            reference.child(apartmentNumber).setValue(payment);
            Toast.makeText(ConfirmPaymentActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}