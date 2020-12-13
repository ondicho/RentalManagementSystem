package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringa.rentalmanagementsystem.R;

import models.Payment;

public class FirebasePaymentListAdapter extends FirebaseRecyclerAdapter<Payment,FirebasePaymentViewHolder> {

    private DatabaseReference mRef;
    private Context mContext;

    private FirebasePaymentListAdapter(@NonNull FirebaseRecyclerOptions<Payment> options,DatabaseReference ref,Context context){
        super(options);
        mRef=ref.getRef();
        mContext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebasePaymentViewHolder firebasePaymentViewHolder, int i, @NonNull Payment payment) {
        firebasePaymentViewHolder.bindPayment(payment);
    }

    @NonNull
    @Override
    public FirebasePaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item,parent,false);
        return new FirebasePaymentViewHolder(view);
    }
}
