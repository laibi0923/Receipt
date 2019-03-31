package com.example.lpc.receipt.NewRecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Record.Record_Item_Adapter;

import java.util.ArrayList;
import java.util.List;

public class Receipt_Adapter extends RecyclerView.Adapter<Receipt_Adapter.Receipt_ViewHolder> {

    private List<Receipt_Item_Model> mData;

    private LayoutInflater mInflater;

    private Record_Item_Adapter.ItemClickListener mClickListener;

    public Receipt_Adapter(Context context, List<Receipt_Item_Model> mData) {
        this.mData = mData;
        this.mInflater = mInflater.from(context);
    }



    @NonNull
    @Override
    public Receipt_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.c01_recordmain_item, parent, false);
        return new Receipt_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Receipt_ViewHolder holder, int position) {
        // 放數據入 ViewHolder
        holder.viewholder_itemname.setText(mData.get(position).getItemName());

        holder.viewholder_itemtype.setText(mData.get(position).getItemType());

        holder.viewholder_itemprice.setText(mData.get(position).getItemPrice());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }







    // ViewHolder
    public class Receipt_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView viewholder_itemtypeimg;

        private TextView viewholder_itemname, viewholder_itemtype, viewholder_itemprice;


        public Receipt_ViewHolder(View itemView) {
            super(itemView);

            viewholder_itemname = itemView.findViewById(R.id.viewholder_itemname);

            viewholder_itemtype = itemView.findViewById(R.id.viewholder_itemtype);

            viewholder_itemprice = itemView.findViewById(R.id.viewholder_itemprice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }



}



