package com.iron.hostingcontroll.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iron.data.entities.ClientEntity;
import com.iron.hostingcontroll.R;
import com.iron.hostingcontroll.databinding.ItemClientBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private List<ClientEntity> list = new ArrayList<>();

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client,parent,false);

        return new ClientsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {

        });
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insertAll(List<ClientEntity> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void insert(ClientEntity entity){
        this.list.add(entity);
        notifyItemInserted(list.size());
    }

    public void insert(ClientEntity entity, int position){
        this.list.add(position,entity);
        notifyItemInserted(position);
    }

    public class ClientsViewHolder extends RecyclerView.ViewHolder{

        private ItemClientBinding binding;

        public ClientsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemClientBinding.bind(itemView);
        }

        public void bind(ClientEntity entity){
            binding.txtTitle.setText(entity.getManager());
            binding.txtSubtitle.setText(entity.getBrand());
        }
    }

}
