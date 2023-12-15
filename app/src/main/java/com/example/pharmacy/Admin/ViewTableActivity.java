package com.example.pharmacy.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityCrudAdminBinding;
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
                            InsertAdminActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("employee_store")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("drug_store")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_category")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_check")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_statistic")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("medicine_unit")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("store_medicine")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("employee_store")) {

                } else if (binding.spinnerTitleTableView.getSelectedItem().toString().equals("position_name")) {

                }
            }
        });
    }
}