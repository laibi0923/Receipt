package com.example.lpc.receipt.Record;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        if(mData.get(position).getProduct_discount().isEmpty()){
           viewHolder.listitem_discount.setVisibility(View.GONE);
        }else {
            viewHolder.listitem_discount_textview.setText(mData.get(position).getProduct_discount());
        }

        if (mData.get(position).getProduct_tax().isEmpty()){
            viewHolder.listitem_tax.setVisibility(View.GONE);
        }else {
            viewHolder.listitem_tax_textview.setText(mData.get(position).getProduct_tax());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        LinearLayout listitem_discount, listitem_tax;

        EditText listitem_productprice;

        TextView listitem_productno, listitem_name, listitem_finalprice, listitem_discount_textview, listitem_tax_textview;

        ViewHolder(View itemView) {

            super(itemView);

            listitem_productno = itemView.findViewById(R.id.listitem_productno);

            listitem_name = itemView.findViewById(R.id.listitem_name);

//            listitem_productprice = itemView.findViewById(R.id.listitem_productprice);

            listitem_discount = itemView.findViewById(R.id.listitem_discount);

            listitem_discount_textview = itemView.findViewById(R.id.listitem_discount_textview);

            listitem_tax = itemView.findViewById(R.id.listitem_tax);

            listitem_tax_textview = itemView.findViewById(R.id.listitem_tax_textview);

            listitem_finalprice = itemView.findViewById(R.id.listitem_finalprice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            // TODO: Implement this method
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String get_Product_No(int id){
        return mData.get(id).getProduct_no();
    }

    String get_Product_Name(int id){
        return mData.get(id).getProduct_noname();
    }

    String get_Product_Price(int id){
        return mData.get(id).getProduct_price();
    }

    String get_Product_Discount(int id){
        return mData.get(id).getProduct_discount();
    }

    String get_Product_Tax(int id){
        return mData.get(id).getProduct_tax();
    }

    String get_Product_FinalPrice(int id){
        return mData.get(id).getProduct_final_price();
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
