package com.sample.recyclerviewbrownbag.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.recyclerviewbrownbag.R;
import com.sample.recyclerviewbrownbag.model.Box;

import java.util.List;

/**
 * Created by S. Reyes on 2/4/16.
 */
public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.BoxViewHolder> {

    private List<Box> boxes;

    public BoxAdapter(List<Box> boxes) {
        this.boxes = boxes;
    }

    @Override
    public BoxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box, parent, false);
        BoxViewHolder boxViewHolder = new BoxViewHolder(v);
        return boxViewHolder;
    }

    @Override
    public void onBindViewHolder(BoxViewHolder holder, int position) {
        final Box box = boxes.get(position);
    }

    @Override
    public int getItemCount() {
        return boxes.size();
    }

    class BoxViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public BoxViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.box);
        }
    }
}
