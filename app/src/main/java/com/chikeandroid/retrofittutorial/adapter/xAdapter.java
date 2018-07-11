package com.chikeandroid.retrofittutorial.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chikeandroid.retrofittutorial.R;
import com.chikeandroid.retrofittutorial.data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chike on 12/4/2016.
 */

public class xAdapter extends RecyclerView.Adapter<xAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;



    public xAdapter(Context context, List<Item> posts) {
        mItems = posts;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivAva;
        public TextView titleTv1;
        public TextView titleTv2;
        public TextView titleTv3;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv1 = itemView.findViewById(R.id.textTest1);
            titleTv2 = itemView.findViewById(R.id.textTest2);
            titleTv3 = itemView.findViewById(R.id.textTest3);
            ivAva = itemView.findViewById(R.id.ivAva);
//            ivAva.setOnClickListener(this);

//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = getItem(getAdapterPosition());
            if (v.getId() == ivAva.getId()) {

            }

        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.simple_list2, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(xAdapter.ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView textView1 = holder.titleTv1;
        TextView textView2 = holder.titleTv2;
        TextView textView3 = holder.titleTv3;
        ImageView iv1 = holder.ivAva;

        textView1.setText(item.getOwner().getDisplayName());
        textView2.setText(item.getOwner().getUserType());
        textView3.setText(item.getOwner().getUserId().toString());
        Picasso.get().load(item.getOwner().getProfileImage())
                .into(iv1);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked IV", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();

    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

}
