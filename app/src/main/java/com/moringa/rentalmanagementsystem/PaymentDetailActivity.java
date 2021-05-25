package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import adapters.PaymentPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class PaymentDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private PaymentPagerAdapter adapterViewPager;
    List<Payment> mInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        ButterKnife.bind(this);

        mInvoice= Parcels.unwrap(getIntent().getParcelableExtra("invoice"));
        int startingPosition=getIntent().getIntExtra("position",0);
        adapterViewPager=new PaymentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mInvoice);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}