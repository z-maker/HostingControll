package com.iron.hostingcontroll.ui.clients;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iron.data.entities.ClientEntity;
import com.iron.hostingcontroll.R;
import com.iron.hostingcontroll.databinding.ClientListFragmentBinding;

import java.util.List;

public class ClientListFragment extends Fragment {

    private static ClientListFragmentBinding binding;

    private ClientListViewModel mViewModel;

    private ClientsAdapter adapter = new ClientsAdapter();

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ClientListFragmentBinding.inflate(inflater,container,false);

        binding.btnAdd.setOnClickListener(v -> {
            ClientForm.newInstance().show(getChildFragmentManager(),ClientForm.TAG);
        });

        mViewModel = new ViewModelProvider(this).get(ClientListViewModel.class);

        mViewModel.getData().observe(getViewLifecycleOwner(), clientEntities -> {
            adapter.insertAll(clientEntities);
        });

        binding.rvClient.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvClient.setHasFixedSize(true);
        binding.rvClient.setAdapter(adapter);

        mViewModel.getAll();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClientListViewModel.class);
        // TODO: Use the ViewModel
    }

}