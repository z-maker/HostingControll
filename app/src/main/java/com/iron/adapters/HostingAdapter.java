package com.iron.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iron.data.entities.HostingConstants;
import com.iron.data.entities.HostingEntity;
import com.iron.hostingcontroll.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        private TextView txtUsername, txtDomain, txtInfo, txtLink;
        private ImageButton ibtHostingStatus,ibtSslStatus,ibtDomainStatus;

        public HostingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.tvHostingName);
            txtDomain = itemView.findViewById(R.id.tvHostingDomain);
            txtInfo = itemView.findViewById(R.id.tvHostingInfo);
            ibtHostingStatus = itemView.findViewById(R.id.ibtHostingStatus);
            ibtSslStatus = itemView.findViewById(R.id.ibtSslStatus);
            ibtDomainStatus = itemView.findViewById(R.id.ibtDomainStatus);
            txtLink = itemView.findViewById(R.id.txtLink);

            txtLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = list.get(getAdapterPosition()).getDomainUrl();

                    boolean reacheble = isConnectedToThisServer(url);

                    if (reacheble) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+url));
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }

        public boolean isConnectedToThisServer(String host) {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 " + host);
                int exitValue = ipProcess.waitFor();
                Log.d("KK",exitValue+"");
                return (exitValue == 0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }

        public void bind(HostingEntity obj){
            txtUsername.setText(obj.getClientName());
            txtDomain.setText(obj.getDomainUrl());
            txtInfo.setText(obj.getDomainCreated());


            switch (obj.getHostingStatus()){
                case HostingConstants.HOSING_ACTIVE:
                    ibtHostingStatus.setImageResource(R.drawable.ic_cloud_queue);
                    break;
                case HostingConstants.HOSTING_SUSPENDED:
                    ibtHostingStatus.setImageResource(R.drawable.ic_cloud_off);
                    break;
                default:
            }

            switch (obj.getDomainStatus()){
                case HostingConstants.DOMAIN_ACTIVE:
                    ibtDomainStatus.setImageResource(R.drawable.ic_cloud_queue);
                    break;
                case HostingConstants.DOMAIN_FREE:
                    ibtDomainStatus.setImageResource(R.drawable.ic_cloud_off);
                    break;

            }
        }
    }
}
