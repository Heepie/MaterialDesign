package com.heepie.matrialdesign;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.heepie.matrialdesign.model.Item;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private View layout, sharedTxt, sharedImg;
    private Item mItem;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(R.color.theme_red_primary_dark, "Hello, Heepie");

        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        layout = findViewById(R.id.layout);
        sharedImg = findViewById(R.id.shared_img);
        sharedTxt = findViewById(R.id.shared_txt);

        ((CircleImageView)sharedImg).setImageResource(mItem.getColorResId());
        ((TextView)sharedTxt).setText(mItem.getName());
    }

    private void initData(@ColorRes int colorResId, String name) {
        mItem = new Item(colorResId, name);
    }

    private void initListener() {
        intent = new Intent(MainActivity.this, NextActivity.class);

        layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                intent.putExtra("model", mItem);

                Pair<View, String> pair1 = Pair.create(sharedImg, sharedImg.getTransitionName());
                Pair<View, String> pair2 = Pair.create(sharedTxt, sharedTxt.getTransitionName());

                ActivityOptionsCompat transitionActivityOptions
                        = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair1, pair2);
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });
    }
}
