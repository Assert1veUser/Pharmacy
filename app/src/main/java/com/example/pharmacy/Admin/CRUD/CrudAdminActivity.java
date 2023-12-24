package com.example.pharmacy.Admin.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityCrudAdminBinding;
import com.google.android.material.chip.Chip;

import java.util.List;

public class CrudAdminActivity extends AppCompatActivity {
    private ActivityCrudAdminBinding binding;
    private String chipCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrudAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String storeId = intentGet.getStringExtra("store_id");
        ArrayAdapter<?> adapterInsert =
                ArrayAdapter.createFromResource(this, R.array.tableListAdminCrudInsert,
                        android.R.layout.simple_spinner_item);
        adapterInsert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAdminCrudElementInsert.setAdapter(adapterInsert);

        ArrayAdapter<?> adapterUpdate =
                ArrayAdapter.createFromResource(this, R.array.tableListAdminCrudUpdate,
                        android.R.layout.simple_spinner_item);
        adapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAdminCrudElementUpdate.setAdapter(adapterUpdate);

        ArrayAdapter<?> adapterDelete =
                ArrayAdapter.createFromResource(this, R.array.tableListAdminCrudDelete,
                        android.R.layout.simple_spinner_item);
        adapterDelete.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAdminCrudElementDelete.setAdapter(adapterDelete);
        binding.chipAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.butNext.setVisibility(View.VISIBLE);
                binding.textViewAdminCrudTable.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementInsert.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementUpdate.setVisibility(View.GONE);
                binding.spinnerAdminCrudElementDelete.setVisibility(View.GONE);
            }
        });
        binding.chipUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.butNext.setVisibility(View.VISIBLE);
                binding.textViewAdminCrudTable.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementUpdate.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementInsert.setVisibility(View.GONE);
                binding.spinnerAdminCrudElementDelete.setVisibility(View.GONE);
            }
        });
        binding.chipDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.butNext.setVisibility(View.VISIBLE);
                binding.textViewAdminCrudTable.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementDelete.setVisibility(View.VISIBLE);
                binding.spinnerAdminCrudElementUpdate.setVisibility(View.GONE);
                binding.spinnerAdminCrudElementInsert.setVisibility(View.GONE);
            }
        });
        binding.butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ids = binding.chipGroupAdminCrud.getCheckedChipIds();
                for (Integer id:ids){
                    Chip chip = binding.chipGroupAdminCrud.findViewById(id);
                    chipCheck = chip.getText().toString();
                }
                if (chipCheck.equals("Добавить")){
                    Intent intent = new Intent(CrudAdminActivity.this,
                            InsertAdminActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("crudTable", binding.spinnerAdminCrudElementInsert.getSelectedItem().toString());
                    startActivity(intent);
                } else if (chipCheck.equals("Изменить")){
                    binding.spinnerAdminCrudElementInsert.setVisibility(View.GONE);
                    binding.spinnerAdminCrudElementUpdate.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(CrudAdminActivity.this,
                            UpdateAdminActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("crudTable", binding.spinnerAdminCrudElementUpdate.getSelectedItem().toString());
                    intent.putExtra("store_id", storeId);
                    startActivity(intent);
                } else if (chipCheck.equals("Удалить")) {
                    binding.spinnerAdminCrudElementInsert.setVisibility(View.GONE);
                    binding.spinnerAdminCrudElementDelete.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(CrudAdminActivity.this,
                            DeleteAdminActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("crudTable", binding.spinnerAdminCrudElementDelete.getSelectedItem().toString());
                    intent.putExtra("store_id", storeId);
                    startActivity(intent);
                }else {
                    Toast.makeText(CrudAdminActivity.this, "Выберите действие", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}