package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.rentalmanagementsystem.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Payment;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.PaymentViewHolder> {

    private List<Payment> mInvoice;
    private Context mContext;

    public PaymentListAdapter(Context context,List<Payment> invoice){
        mContext=context;
        mInvoice=invoice;
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.monthTextView) TextView mMonthTextView;
        @BindView(R.id.apartmentNumberTextView) TextView mApartmentNumberTextView;
        @BindView(R.id.transactionCodeTextView) TextView mTransactionCodeTextView;
        @BindView(R.id.amountTextView) TextView mAmountTextView;

        private Context mContext;

        public PaymentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext=itemView.getContext();
        }

        public void bindPayment(Payment invoice){
            mApartmentNumberTextView.setText(invoice.getApartmentNo());
            mMonthTextView.setText(invoice.getMonth());
            mTransactionCodeTextView.setText(invoice.getTransactionCode());
            mAmountTextView.setText(invoice.getAmount());
        }

    }

    @NonNull
    @Override
    public PaymentListAdapter.PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item,parent,false);
       PaymentViewHolder viewHolder=new PaymentViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentListAdapter.PaymentViewHolder holder, int position) {
        holder.bindPayment(mInvoice.get(position));
    }

    @Override
    public int getItemCount() {
        return mInvoice.size();
    }
}
