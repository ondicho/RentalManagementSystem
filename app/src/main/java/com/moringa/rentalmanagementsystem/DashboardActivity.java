package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.tenantInformationImageView) ImageView tenantImageView;
    @BindView(R.id.rentStatusImageView) ImageView statusImageView;
    @BindView(R.id.confirmPaymentImageView) ImageView paymentImageView;
    @BindView(R.id.aboutUsImagemageView) ImageView aboutUsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        tenantImageView.setOnClickListener(this);
        statusImageView.setOnClickListener(this);
        paymentImageView.setOnClickListener(this);
        aboutUsImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==tenantImageView){
            Intent intent=new Intent(DashboardActivity.this,TenantInformationActivity.class);
            startActivity(intent);
        }
        if(v==statusImageView){
            Intent intent=new Intent(DashboardActivity.this,RentStatusActivity.class);
            startActivity(intent);
        }
        if(v==paymentImageView){
            Intent intent=new Intent(DashboardActivity.this,ConfirmPaymentActivity.class);
            startActivity(intent);
        }
        if(v==aboutUsImageView){
            Intent intent=new Intent(DashboardActivity.this,AboutUsActivity.class);
            startActivity(intent);
        }
    }
}