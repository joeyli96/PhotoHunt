package com.ubc.dank.photohunt;

/**
 * Created by camelliapeng on 16-02-28.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class UpdateSplashdown extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String[] tagList = {"computer", "cool stuff", "books", "nerds","ourselves"};
    private static Map<String, Integer> playerScore;
    private static Map<String, List<Byte[]>> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashdown);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true); //has fixed size

        //specify layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify adapter
        mAdapter = new SplashdownAdapter(tagList);
        mRecyclerView.setAdapter(mAdapter);

        ((TextView)findViewById(R.id.overall_score)).setText("player 1 score - player 2 score");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setFinalData(Map<String, Integer> playerScore, Map<String, List<Byte[]>> imageList){
        UpdateSplashdown.playerScore = playerScore;
        UpdateSplashdown.imageList = imageList;
    }
}
