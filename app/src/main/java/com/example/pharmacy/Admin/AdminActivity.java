package com.example.pharmacy.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacy.Admin.CRUD.CrudAdminActivity;
import com.example.pharmacy.Admin.ViewTable.ViewTableActivity;
import com.example.pharmacy.databinding.ActivityAdminBinding;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Joiner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private String surname;
    private String patronymic;
    private String name;
    private String namePatronymic;
    private String address;
    private String postcode;
    private String employeeIdNew;
    private String storeIdNew;
    private List<String> listEmployee = new ArrayList<String>();
    private List<String> listEmployeeId = new ArrayList<String>();
    private List<String> listStoreId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String storeId = intentGet.getStringExtra("storeId");
        String employeeId = intentGet.getStringExtra("employeeId");

        Thread gfgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    Connection connection = DriverManager.getConnection(
                            "jdbc:postgresql://" +
                                    ipAddress + ":5432/pharmacy",
                            loginGet,
                            passwordGet);
                    Statement statement = connection.createStatement();
                    System.out.println("DataBase start");
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employee " +
                            "WHERE id = " + employeeId + ";");
                    while (resultSet.next()) {
                        surname = resultSet.getString("surname");
                        name = resultSet.getString("name");
                        patronymic = resultSet.getString("patronymic");
                    }

                    ResultSet resultSet1 = statement.executeQuery("SELECT * FROM drug_store " +
                            "WHERE id = " + storeId + ";");
                    while (resultSet1.next()) {
                        address = resultSet1.getString("address");
                        postcode = resultSet1.getString("postcode");
                    }
                    resultSet.close();
                    resultSet1.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        gfgThread.start();
        try {
            gfgThread.join();
            listEmployee.add(name + " ");
            listEmployee.add(patronymic);
            namePatronymic = Joiner.on("\t").join(listEmployee);
            binding.textViewThisEmployeeSurname.setText(surname);
            binding.textViewThisEmployeeNamePatronymic.setText(namePatronymic);
            listEmployeeId.add("ID: " + employeeId);
            employeeIdNew = Joiner.on("").join(listEmployeeId);
            binding.textViewThisEmployeeId.setText(employeeIdNew);
            listStoreId.add("#" + postcode);
            storeIdNew = Joiner.on("").join(listStoreId);
            binding.textViewThisEmployeeStoreId.setText(storeIdNew);
            binding.textViewThisEmployeeStoreAddress.setText(address);

            binding.butNewEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminActivity.this,
                            NewEmployeeActivty.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                }
            });
            binding.butTableFix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminActivity.this,
                            CrudAdminActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("store_id", storeId);
                    startActivity(intent);
                }
            });
            binding.butAllTableCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminActivity.this,
                            ViewTableActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}