package com.heepie.matrialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.heepie.matrialdesign.model.Item;

import java.util.Arrays;
import java.util.List;

public class RecyclerMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView titleTxtView;
    private List<Item> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        initView();
        makeDummyData();
        initRecyclerView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        titleTxtView = findViewById(R.id.titleTxtView);
    }

    private void initRecyclerView() {
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        adapter.setDataAndRefresh(data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void makeDummyData() {
        data = Arrays.asList(
                new Item(android.R.color.holo_orange_light, "Hello"),
                new Item(android.R.color.holo_blue_dark, "Heepie"),
                new Item(android.R.color.holo_red_light, "YoLo"),
                new Item(android.R.color.holo_green_light, "GooD")
        );
    }


}
