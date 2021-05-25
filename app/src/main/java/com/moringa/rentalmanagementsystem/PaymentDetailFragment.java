package com.moringa.rentalmanagementsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentDetailFragment extends Fragment {
    @BindView(R.id.apartmentNumTextView) TextView mApartmentNumTextView;
    @BindView(R.id.monthTextView) TextView mMonthTextView;
    @BindView(R.id.transactionCodeTextView) TextView mTransactionCodeTextView;
    @BindView(R.id.amountTextView) TextView mAmountTetxView;

    private Payment invoice;
    public PaymentDetailFragment(){

    }
    public static PaymentDetailFragment newInstance(Payment invoice) {
        PaymentDetailFragment fragment = new PaymentDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("invoice",Parcels.wrap(invoice));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invoice=Parcels.unwrap(getArguments().getParcelable("invoice"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_payment_detail,container,false);
        ButterKnife.bind(this,view);

        mApartmentNumTextView.setText(invoice.getApartmentNo());
        mMonthTextView.setText(invoice.getMonth());
        mTransactionCodeTextView.setText(invoice.getTransactionCode());
        mAmountTetxView.setText(invoice.getAmount());

        return view;
    }
}