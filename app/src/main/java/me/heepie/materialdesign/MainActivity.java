package me.heepie.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.heepie.materialdesign.model.Item;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Heepie on 2018. 1. 3.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeDummyData();

        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initRecyclerView() {
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        adapter.setDataAndRefresh(data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void makeDummyData() {
        data = Arrays.asList(
                new Item(R.color.theme_yellow_primary_dark, "Hello"),
                new Item(R.color.theme_blue_primary_dark, "Heepie"),
                new Item(R.color.theme_red_primary_dark, "YoLo"),
                new Item(R.color.theme_green_primary_dark, "GooD")
        );
    }
}
