package com.example.lpc.receipt.Record;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lpc.receipt.R;

import java.util.List;


public class Record_Item_Adapter extends RecyclerView.Adapter<Record_Item_Adapter.ViewHolder> {

    private List<Record_Item_Model> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    Record_Item_Adapter(Context context, List<Record_Item_Model> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.a004_record_listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.listitem_productno.setText(mData.get(position).getProduct_no());

        viewHolder.listitem_name.setText(mData.get(position).getProduct_noname());

        viewHolder.listitem_finalprice.setText(mData.get(position).getProduct_final_price());

        viewHolder.listitem_discount.setText(mData.get(position).getProduct_discount());

        viewHolder.listitem_tax.setText(mData.get(position).getProduct_tax());


    }

    @Override
    public int getItemCount() {
        return mData.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView listitem_productno, listitem_name, listitem_finalprice, listitem_discount, listitem_tax;

        ViewHolder(View itemView) {

            super(itemView);

            listitem_productno = itemView.findViewById(R.id.listitem_productno);

            listitem_name = itemView.findViewById(R.id.listitem_name);

            listitem_finalprice = itemView.findViewById(R.id.listitem_finalprice);

            listitem_discount = itemView.findViewById(R.id.listitem_discount);

            listitem_tax = itemView.findViewById(R.id.listitem_tax);

            //temView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            // TODO: Implement this method
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getProduct_discount();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}