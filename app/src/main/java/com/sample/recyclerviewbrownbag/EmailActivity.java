package com.sample.recyclerviewbrownbag;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.sample.recyclerviewbrownbag.adapter.EmailAdapter;
import com.sample.recyclerviewbrownbag.animator.BaseItemAnimator;
import com.sample.recyclerviewbrownbag.animator.FadeInAnimator;
import com.sample.recyclerviewbrownbag.animator.FadeInDownAnimator;
import com.sample.recyclerviewbrownbag.animator.FadeInLeftAnimator;
import com.sample.recyclerviewbrownbag.animator.FadeInRightAnimator;
import com.sample.recyclerviewbrownbag.animator.FadeInUpAnimator;
import com.sample.recyclerviewbrownbag.animator.FlipInBottomXAnimator;
import com.sample.recyclerviewbrownbag.animator.FlipInLeftYAnimator;
import com.sample.recyclerviewbrownbag.animator.FlipInRightYAnimator;
import com.sample.recyclerviewbrownbag.animator.FlipInTopXAnimator;
import com.sample.recyclerviewbrownbag.animator.LandingAnimator;
import com.sample.recyclerviewbrownbag.animator.OvershootInLeftAnimator;
import com.sample.recyclerviewbrownbag.animator.OvershootInRightAnimator;
import com.sample.recyclerviewbrownbag.animator.ScaleInAnimator;
import com.sample.recyclerviewbrownbag.animator.ScaleInBottomAnimator;
import com.sample.recyclerviewbrownbag.animator.ScaleInLeftAnimator;
import com.sample.recyclerviewbrownbag.animator.ScaleInRightAnimator;
import com.sample.recyclerviewbrownbag.animator.ScaleInTopAnimator;
import com.sample.recyclerviewbrownbag.animator.SlideInDownAnimator;
import com.sample.recyclerviewbrownbag.animator.SlideInLeftAnimator;
import com.sample.recyclerviewbrownbag.animator.SlideInRightAnimator;
import com.sample.recyclerviewbrownbag.animator.SlideInUpAnimator;
import com.sample.recyclerviewbrownbag.decoration.BoxOverlayItemDecoration;
import com.sample.recyclerviewbrownbag.decoration.DividerItemDecoration;
import com.sample.recyclerviewbrownbag.model.Email;
import com.sample.recyclerviewbrownbag.util.DummyDataGenerator;

/**
 * Created by S. Reyes on 2/1/16.
 */
public class EmailActivity extends Activity {

    private RecyclerView recyclerView;
    private Button addBtn, removeBtn;
    private EmailAdapter adapter;

    enum Type {
        FadeIn(new FadeInAnimator(new OvershootInterpolator(1f))),
        FadeInDown(new FadeInDownAnimator(new OvershootInterpolator(1f))),
        FadeInUp(new FadeInUpAnimator(new OvershootInterpolator(1f))),
        FadeInLeft(new FadeInLeftAnimator(new OvershootInterpolator(1f))),
        FadeInRight(new FadeInRightAnimator(new OvershootInterpolator(1f))),
        Landing(new LandingAnimator(new OvershootInterpolator(1f))),
        ScaleIn(new ScaleInAnimator(new OvershootInterpolator(1f))),
        ScaleInTop(new ScaleInTopAnimator(new OvershootInterpolator(1f))),
        ScaleInBottom(new ScaleInBottomAnimator(new OvershootInterpolator(1f))),
        ScaleInLeft(new ScaleInLeftAnimator(new OvershootInterpolator(1f))),
        ScaleInRight(new ScaleInRightAnimator(new OvershootInterpolator(1f))),
        FlipInTopX(new FlipInTopXAnimator(new OvershootInterpolator(1f))),
        FlipInBottomX(new FlipInBottomXAnimator(new OvershootInterpolator(1f))),
        FlipInLeftY(new FlipInLeftYAnimator(new OvershootInterpolator(1f))),
        FlipInRightY(new FlipInRightYAnimator(new OvershootInterpolator(1f))),
        SlideInLeft(new SlideInLeftAnimator(new OvershootInterpolator(1f))),
        SlideInRight(new SlideInRightAnimator(new OvershootInterpolator(1f))),
        SlideInDown(new SlideInDownAnimator(new OvershootInterpolator(1f))),
        SlideInUp(new SlideInUpAnimator(new OvershootInterpolator(1f))),
        OvershootInRight(new OvershootInRightAnimator(1.0f)),
        OvershootInLeft(new OvershootInLeftAnimator(1.0f));

        private BaseItemAnimator mAnimator;

        Type(BaseItemAnimator animator) {
            mAnimator = animator;
        }

        public BaseItemAnimator getAnimator() {
            return mAnimator;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Spinner spinner = (Spinner) findViewById(R.id.anim_spinner);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.name());
        }

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerView.setItemAnimator(Type.values()[position].getAnimator());
                recyclerView.getItemAnimator().setAddDuration(500);
                recyclerView.getItemAnimator().setRemoveDuration(500);
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Layout Manager
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        renderRecyclerView(linearLayoutManager);

        addBtn = (Button) findViewById(R.id.button_add);
        removeBtn = (Button) findViewById(R.id.button_del);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addEmail(DummyDataGenerator.generateEmail(Email.Status.Unread), 0);
                recyclerView.scrollToPosition(0);
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getItemCount() > 0)
                    adapter.removeEmail(0);
            }
        });
        removeBtn.setVisibility(View.GONE);
    }

    private void renderRecyclerView(RecyclerView.LayoutManager layoutManager) {
        recyclerView.removeAllViews();

        recyclerView.setLayoutManager(layoutManager);

        // Adapters
        adapter = new EmailAdapter(this, DummyDataGenerator.generateDummmyDatas(15));
        recyclerView.setAdapter(adapter);

        // ItemAnimators
        recyclerView.setItemAnimator(new OvershootInLeftAnimator());

        // ItemDecorators
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.addItemDecoration(new BoxOverlayItemDecoration(this));

    }
}
