package me.heepie.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.heepie.materialdesign.model.Item;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Heepie on 2018. 1. 3.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    private Activity activity;
    private List<Item> mData;

    RecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    void setDataAndRefresh(List<Item> mData) {
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
        Log.d("onBindViewHolder", "onBindViewHolder: " + mData.get(position).getName());
        holder.setItem(mData.get(position));
        holder.setDataToScreen();
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private View layout, sharedTxt, sharedImg;
        private Item mItem;
        Intent intent;

        Holder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            sharedImg = itemView.findViewById(R.id.shared_img);
            sharedTxt = itemView.findViewById(R.id.shared_txt);

            initListener();
        }

        private void initListener() {
            intent = new Intent(activity, DetailActivity.class);

            layout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Pair<View, String> pair1 = Pair.create(sharedImg, sharedImg.getTransitionName());
                    Pair<View, String> pair2 = Pair.create(sharedTxt, sharedTxt.getTransitionName());

                    ActivityOptionsCompat transitionActivityOptions
                            = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1, pair2);
                    activity.startActivity(intent, transitionActivityOptions.toBundle());
                }
            });
        }

        private void setItem(Item item) {
            this.mItem = item;
            intent.putExtra("model", item);
        }

        private void setDataToScreen() {
            ((CircleImageView) sharedImg).setImageResource(mItem.getColorResId());
            ((TextView) sharedTxt).setText(mItem.getName());
        }
    }
}
