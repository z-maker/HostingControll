package com.iron.hostingcontroll.ui.clients;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.iron.data.entities.ClientEntity;
import com.iron.hostingcontroll.databinding.FragmentClientFormBinding;

import java.util.Date;


/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
public class ClientForm extends BottomSheetDialogFragment {

    public static String TAG = "ClientForm";

    private FragmentClientFormBinding binding;

    public static ClientForm newInstance(){
        return new ClientForm();
    }

    private ClientListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentClientFormBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(ClientListViewModel.class);

        binding.btnSave.setOnClickListener(v -> save());

        return binding.getRoot();
    }

    private void save(){
        ClientEntity entity = new ClientEntity();
        entity.setBrand(binding.txtBrand.getEditText().getText().toString());
        entity.setManager(binding.txtClient.getEditText().getText().toString());
        entity.setPhone(binding.txtPhone.getEditText().getText().toString());
        entity.setEmail(binding.txtEmail.getEditText().getText().toString());
        entity.setJoined(System.currentTimeMillis());
        viewModel.insert(entity);
    }
}
