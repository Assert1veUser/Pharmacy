package com.example.pharmacy.Admin.ViewTable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.pharmacy.Admin.CRUD.InsertAdminActivity;
import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityViewTableBinding;

public class ViewTableActivity extends AppCompatActivity {

    private ActivityViewTableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        ArrayAdapter<?> adapterInsert =
                ArrayAdapter.createFromResource(this, R.array.tableListAdminTableView,
                        android.R.layout.simple_spinner_item);
        adapterInsert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTitleTableView.setAdapter(adapterInsert);

        binding.butCheckTableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("employee")){
                    Intent intent = new Intent(ViewTableActivity.this,
                            EmployeeViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("employee_store")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            EmployeeStoreViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("drug_store")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            DrugStoreViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            MedicineViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_category")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            MedicineCategoryViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_check")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            MedicineCheckViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_statistic")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            MedicineStatisticViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_unit")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            MedicineUnitViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("store_medicine")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            StoreMedicineViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("position_name")) {
                    Intent intent = new Intent(ViewTableActivity.this,
                            PositionNameViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                }
            }
        });
    }
}