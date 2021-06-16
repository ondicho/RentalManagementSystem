package com.moringa.rentalmanagementsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;
import payment.DarajaListener;
import payment.Utils.TransactionType;
import payment.models.AccessToken;
import payment.models.LNMExpress;
import payment.models.LNMResult;

public class Lipa_na_mpesa extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.mpesaAmountEditText) EditText mpesaAmountEditText;
    @BindView(R.id.paymentButton) Button paymentButton;

    Payment payment;

    String phoneNumber;
    String amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lipa_na_mpesa);
        ButterKnife.bind(this);


        payment=Payment.with("6KSeCUybpCKmTi6s84W56PvBAA35I7Al","0M4KR4ODr0w52Iv7", new DarajaListener<AccessToken>(){
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(Lipa_na_mpesa.this.getClass().getSimpleName(), accessToken.getAccess_token());
                Toast.makeText(Lipa_na_mpesa.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e(Lipa_na_mpesa.this.getClass().getSimpleName(), error);
            }
        });
    }

    @Override
    public void onClick(View v) {
    if (v==paymentButton){
        phoneNumber = editTextPhoneNumber.getText().toString().trim();
        amount=mpesaAmountEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            editTextPhoneNumber.setError("Please Provide a Phone Number");
            return;
        }

        //TODO :: REPLACE WITH YOUR OWN CREDENTIALS  :: THIS IS SANDBOX DEMO
        LNMExpress lnmExpress = new LNMExpress(
                "174379",
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                TransactionType.CustomerBuyGoodsOnline, // TransactionType.CustomerPayBillOnline  <- Apply any of these two
                amount,
                "254708374149",
                "174379",
                phoneNumber,
                "http://mycallbackurl.com/checkout.php",
                "001ABC",
                "Goods Payment"
        );

        //This is the
        payment.requestMPESAExpress(lnmExpress,
                new DarajaListener<LNMResult>() {
                    @Override
                    public void onResult(@NonNull LNMResult lnmResult) {
                        Log.i(Lipa_na_mpesa.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                    }

                    @Override
                    public void onError(String error) {
                        Log.i(Lipa_na_mpesa.this.getClass().getSimpleName(), error);
                    }
                }
        ); }
    }
}

