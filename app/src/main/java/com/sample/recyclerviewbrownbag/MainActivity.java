package com.sample.recyclerviewbrownbag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by S. Reyes on 2/4/16.
 */
public class MainActivity extends Activity {

    private Button lManagerBtn, animBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lManagerBtn = (Button) findViewById(R.id.btn_lmgr);
        animBtn = (Button) findViewById(R.id.btn_anim);

        lManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, LayoutManagerActivity.class);
                startActivity(myIntent);
            }
        });
        animBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, EmailActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
