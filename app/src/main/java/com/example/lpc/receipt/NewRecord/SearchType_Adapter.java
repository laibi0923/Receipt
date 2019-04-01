package com.example.lpc.receipt.NewRecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Record.Record_Item_Adapter;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;
 
public class SearchType_Adapter extends RecyclerView.Adapter<SearchType_Adapter.SearchType_ViewHolder> {

    private List<SearchType_Item_Model> mData;

    private LayoutInflater mInflater;

    private ItemClickListener mClickListener;

    public SearchType_Adapter(Context context, List<SearchType_Item_Model> mData) {
        this.mData = mData;
        this.mInflater = mInflater.from(context);
    }


    @NonNull
    @Override
    public SearchType_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.c02_searchtype_item, parent, false);
        return new SearchType_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchType_ViewHolder holder, int position) {

        holder.searchtype_name.setText(mData.get(position).getSearchtype_name());

        holder.searchtype_content.setText(mData.get(position).getSearchtype_content());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    // ViewHolder
    public class SearchType_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView searchtype_name, searchtype_content;

        public SearchType_ViewHolder(View itemView) {
            super(itemView);

            searchtype_name = itemView.findViewById(R.id.searchtype_name);

            searchtype_content = itemView.findViewById(R.id.searchtype_content);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
			if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public String get_SearchType_Name(int id){
        return mData.get(id).getSearchtype_name();
    }

    public String get_SearchType_Content(int id){
        return mData.get(id).getSearchtype_content();
    }

}
