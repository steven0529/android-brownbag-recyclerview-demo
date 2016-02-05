package com.sample.recyclerviewbrownbag;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sample.recyclerviewbrownbag.adapter.BoxAdapter;
import com.sample.recyclerviewbrownbag.adapter.EmailAdapter;
import com.sample.recyclerviewbrownbag.animator.OvershootInLeftAnimator;
import com.sample.recyclerviewbrownbag.decoration.SimpleDividerItemDecoration;
import com.sample.recyclerviewbrownbag.util.DummyDataGenerator;

/**
 * Created by S. Reyes on 2/4/16.
 */
public class LayoutManagerActivity extends Activity {

    private String[] layoutManagers = {"Linear Vertical",
            "Linear Horizontal",
            "2 Column Grid",
            "Custom Grid"};

    private RecyclerView recyclerView;
    private BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Spinner layoutSpinner = (Spinner) findViewById(R.id.layout_spinner);
        ArrayAdapter<String> layoutSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (String name : layoutManagers) {
            layoutSpinnerAdapter.add(name);
        }
        layoutSpinner.setAdapter(layoutSpinnerAdapter);
        layoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RecyclerView.LayoutManager layoutManager = null;
                if (layoutManagers[position].equals("Linear Horizontal")) {
                    layoutManager = new LinearLayoutManager(LayoutManagerActivity.this);
                    ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
                } else if (layoutManagers[position].equals("Linear Vertical")) {
                    layoutManager = new LinearLayoutManager(LayoutManagerActivity.this);
                    ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
                } else if (layoutManagers[position].equals("2 Column Grid")) {
                    layoutManager = new GridLayoutManager(LayoutManagerActivity.this, 2);
                } else if (layoutManagers[position].equals("Custom Grid")) {
                    layoutManager = new GridLayoutManager(LayoutManagerActivity.this, 2);
                    ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            // Stagger every other row
                            return (position % 3 == 0 ? 2 : 1);
                        }
                    });
                }
                renderRecyclerView(layoutManager);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        renderRecyclerView(linearLayoutManager);

    }

    private void renderRecyclerView(RecyclerView.LayoutManager layoutManager) {
        recyclerView.removeAllViews();

        recyclerView.setLayoutManager(layoutManager);

        boxAdapter = new BoxAdapter(DummyDataGenerator.generateBoxes(30));
        recyclerView.setAdapter(boxAdapter);

    }

}
