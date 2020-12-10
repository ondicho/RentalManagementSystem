package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.apartmentNumberEditText) EditText mApartmentNumberEditText;
    @BindView(R.id.monthEditText) EditText mMonthEditText;
    @BindView(R.id.transactionCodeEditText) EditText mTransactionCodeEditText;
    @BindView(R.id.amountEditText) EditText mAmountEditText;
    @BindView(R.id.confirmPaymentButton) Button mConfirmPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        ButterKnife.bind(this);

        mConfirmPaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}