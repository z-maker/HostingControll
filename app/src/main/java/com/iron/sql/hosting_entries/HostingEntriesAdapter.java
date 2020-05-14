package com.iron.sql.hosting_entries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iron.hostingcontroll.R;
import com.iron.model.HostingEntry;

import java.util.ArrayList;

public class HostingEntriesAdapter extends RecyclerView.Adapter<HostingEntriesAdapter.HostingEntriesVH> {

    private ArrayList<HostingEntry> list = new ArrayList<>();

    public HostingEntriesAdapter(ArrayList<HostingEntry> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HostingEntriesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hosting_entry,parent,false);

        return new HostingEntriesVH(item);
    }

    @Override
    public void onBindViewHolder(@NonNull HostingEntriesVH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HostingEntriesVH extends RecyclerView.ViewHolder{

        private TextView txtUsername, txtDomain, txtInfo;
        private ImageView imgStatus;


        public HostingEntriesVH(@NonNull View itemView) {
            super(itemView);

            txtUsername = itemView.findViewById(R.id.tvHostingName);
            txtDomain = itemView.findViewById(R.id.tvHostingDomain);
            txtInfo = itemView.findViewById(R.id.tvHostingInfo);
            imgStatus = itemView.findViewById(R.id.imgStatus);

        }

        public void bind(HostingEntry obj){
            txtUsername.setText(obj.getUsername());
            txtDomain.setText(obj.getDomain());
            txtInfo.setText(obj.getAdminEmail());

            if (obj.isSuspended()){
                imgStatus.setImageResource(R.drawable.ic_suspend);
            }else {
                imgStatus.setImageResource(R.drawable.ic_check);
            }

        }
    }

}
