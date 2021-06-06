package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringa.rentalmanagementsystem.R;

import butterknife.BindView;

public class Lipa_na_mpesa extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.paymentButton) Button paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_na_mpesa);
    }

    @Override
    public void onClick(View v) {
    if (v==paymentButton){

    }

    }
}