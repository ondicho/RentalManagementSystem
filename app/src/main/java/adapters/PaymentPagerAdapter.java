package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringa.rentalmanagementsystem.PaymentDetailFragment;

import java.util.List;

import models.Payment;

public class PaymentPagerAdapter extends FragmentPagerAdapter{
        private List<Payment> mInvoice;

    public PaymentPagerAdapter(@NonNull FragmentManager fm, int behavior,List<Payment> invoice) {
        super(fm, behavior);
        mInvoice=invoice;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PaymentDetailFragment.newInstance(mInvoice.get(position));
    }

    @Override
    public int getCount() {
        return mInvoice.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mInvoice.get(position).getApartmentNo();
    }
}

