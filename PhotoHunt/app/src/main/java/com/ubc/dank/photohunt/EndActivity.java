package com.ubc.dank.photohunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 2016-02-28.
 */
public class EndActivity extends Activity {
    private Button mStartSinglePlayerButton;
    private TextView mTextView;
    private int finalScore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        finalScore = getIntent().getIntExtra("score",-1);
        mTextView = (TextView) findViewById(R.id.score_textview);

        mStartSinglePlayerButton = (Button) findViewById(R.id.play_again);
        mStartSinglePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });
        mTextView.setText(Integer.toString(finalScore));
    }

    public void startActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
