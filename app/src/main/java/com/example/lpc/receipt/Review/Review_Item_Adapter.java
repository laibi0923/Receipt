package com.example.lpc.receipt.Review;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lpc.receipt.R;

import java.util.List; 
 
public class Review_Item_Adapter extends RecyclerView.Adapter<Review_Item_Adapter.ViewHolder> {

    private List<Review_Item_Model> mData;

    private LayoutInflater mInflater;

    private ItemClickListener mClickListener;

    public Review_Item_Adapter(Context context, List<Review_Item_Model> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.b002_review_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.reviewmain_createdate.setText(mData.get(position).getCreate_Time());

        viewHolder.reviewmain_itemname.setText(mData.get(position).getItem_Name());

        viewHolder.reviewmain_price.setText(mData.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView reviewmain_createdate;

        TextView reviewmain_itemname;

        TextView reviewmain_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewmain_createdate = itemView.findViewById(R.id.reviewmain_createdate);

            reviewmain_itemname = itemView.findViewById(R.id.reviewmain_itemname);

            reviewmain_price = itemView.findViewById(R.id.reviewmain_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void addData(int Position, Review_Item_Model Model){
        mData.add(Position, Model);
        notifyItemInserted(Position);
    }

    public void removeData(int Position){
        mData.remove(Position);
        notifyItemRemoved(Position);
    }


}
