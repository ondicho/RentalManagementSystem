package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.rentalmanagementsystem.PaymentDetailActivity;
import com.moringa.rentalmanagementsystem.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import Constants.Constants;
import models.Payment;

public class FirebasePaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebasePaymentViewHolder(View itemView){
        super(itemView);
        mView=itemView;
        mContext=itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPayment(Payment payment){
        TextView apartmentNumberTextView=(TextView)mView.findViewById(R.id.apartmentNumberTextView);
        TextView monthTextView=(TextView)mView.findViewById(R.id.monthTextView);
        TextView transactionCodeTextView=(TextView)mView.findViewById(R.id.transactionCodeTextView);
        TextView amountTextView=(TextView)mView.findViewById(R.id.amountTextView);
    }
    @Override
    public void onClick(View v) {
        final ArrayList<Payment> invoice=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PAYMENTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot paymentSnapShot:snapshot.getChildren()){
                    invoice.add(paymentSnapShot.getValue(Payment.class));
                }

                int itemPosition=getLayoutPosition();
                Intent intent=new Intent(mContext, PaymentDetailActivity.class);
                intent.putExtra("position",itemPosition + "");
                intent.putExtra("invoice", Parcels.wrap(invoice));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
