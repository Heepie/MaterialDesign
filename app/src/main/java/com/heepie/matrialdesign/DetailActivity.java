package com.heepie.matrialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.heepie.matrialdesign.model.Item;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends Activity {

    private CircleImageView imgView;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
    }

    private void initData() {
        Item item = getIntent().getParcelableExtra("item");
        imgView.setImageResource(item.getColorResId());
        txtView.setText(item.getName());
    }

    private void initView() {
        imgView = findViewById(R.id.imgView);
        txtView = findViewById(R.id.txtView);
    }
}
