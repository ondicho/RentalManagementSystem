package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringa.rentalmanagementsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lipa_na_mpesa extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.mpesaAmountEditText) EditText mpesaAmountEditText;
    @BindView(R.id.paymentButton) Button paymentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_na_mpesa);
        ButterKnife.bind(this);

        int Amount= Integer.valueOf(mpesaAmountEditText.getText().toString());

    }

    @Override
    public void onClick(View v) {
    if (v==paymentButton){

        }
    }

}
