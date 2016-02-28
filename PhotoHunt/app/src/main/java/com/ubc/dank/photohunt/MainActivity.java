package com.ubc.dank.photohunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mStartSinglePlayerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartSinglePlayerButton = (Button) findViewById(R.id.button_single_player);
        mStartSinglePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });
    }

    public void startActivity() {
        Intent intent = new Intent(this,CameraActivity.class);
        startActivity(intent);
    }
}
