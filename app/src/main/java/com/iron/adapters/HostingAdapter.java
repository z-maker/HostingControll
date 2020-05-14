package com.iron.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iron.data.entities.HostingEntity;
import com.iron.hostingcontroll.R;

import java.util.ArrayList;
import java.util.List;

public class HostingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HostingEntity> list = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hosting_entry, parent, false);

        return new HostingViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HostingViewHolder vh = (HostingViewHolder) holder;
        vh.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<HostingEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public HostingEntity getObjectAt(int index){
        return list.get(index);
    }

    private class HostingViewHolder extends RecyclerView.ViewHolder{

        private HostingEntity obj;

        public HostingViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(HostingEntity obj){

        }
    }
}
