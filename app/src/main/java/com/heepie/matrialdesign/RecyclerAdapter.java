package com.heepie.matrialdesign;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heepie.matrialdesign.model.Item;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heepie on 2018. 1. 3..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    Activity activity;
    List<Item> mData;

    public RecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setDataAndRefresh(List<Item> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_post, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setItem(mData.get(position));
        holder.setDataToScreen();
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private Item mItem;
        private View imgView;
        private View txtView;
        Intent intent;

        public Holder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            txtView = itemView.findViewById(R.id.txtView);

            initListener();
        }

        private void initListener() {
            Pair<View, String> pair1 = Pair.create(imgView, imgView.getTransitionName());
//            Pair<View, String> pair2 = Pair.create(txtView, txtView.getTransitionName());

            final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1);

            intent = new Intent(activity, DetailActivity.class);
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(intent, options.toBundle());
                }
            });
        }

        public void setItem(Item mItem) {
            this.mItem = mItem;
            intent.putExtra("item", mItem);
        }

        public void setDataToScreen() {
            ((CircleImageView)imgView).setImageResource(mItem.getColorResId());
            ((TextView)txtView).setText(mItem.getName());
        }
    }
}
