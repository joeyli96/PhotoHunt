package com.ubc.dank.photohunt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by camelliapeng on 16-02-28.
 */
public class SplashdownAdapter extends RecyclerView.Adapter<SplashdownAdapter.ViewHolder> {
    String[] tagList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tag;

        public ViewHolder(View v){
            super(v);
            this.tag = (TextView)v.findViewById(R.id.tag);
        }
    }

    public SplashdownAdapter(String[] tagList){
        this.tagList = tagList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.splashdown_history, parent, false);
        ViewHolder vh = new ViewHolder(newView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tag.setText(tagList[position]);
        //need to do something here to update user's pictures and tag
    }

    @Override
    public int getItemCount() {
        return tagList.length;
    }

}
