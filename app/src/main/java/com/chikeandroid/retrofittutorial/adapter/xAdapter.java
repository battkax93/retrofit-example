package com.chikeandroid.retrofittutorial.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chikeandroid.retrofittutorial.R;
import com.chikeandroid.retrofittutorial.data.model.Item;
import com.chikeandroid.retrofittutorial.data.model.Owner;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Chike on 12/4/2016.
 */

public class xAdapter extends RecyclerView.Adapter<xAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;


    public xAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView ivAva;
        public TextView titleTv1;
        public TextView titleTv2;
        public TextView titleTv3;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv1 = (TextView) itemView.findViewById(R.id.textTest1);
            titleTv2 = (TextView) itemView.findViewById(R.id.textTest2);
            titleTv3 = (TextView) itemView.findViewById(R.id.textTest3);
            ivAva = (ImageView) itemView.findViewById(R.id.ivAva);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getAnswerId());

            notifyDataSetChanged();

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.simple_list2, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(xAdapter.ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView textView1 = holder.titleTv1;
        TextView textView2 = holder.titleTv2;
        TextView textView3 = holder.titleTv3;
        ImageView iv1 = holder.ivAva;

        textView1.setText(item.getOwner().getDisplayName());
        textView2.setText(item.getOwner().getDisplayName());
        textView3.setText(item.getOwner().getDisplayName());
        Picasso.get()
                .load(item.getOwner().getProfileImage())
                .fit()
                .into(iv1);
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

    public interface PostItemListener {
        void onPostClick(long id);
    }

}
