package com.example.pharmacy.Pharmacist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmacy.databinding.ActivityAdminBinding;
import com.example.pharmacy.databinding.ActivityPharmacistBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PharmacistActivity extends AppCompatActivity {
    private ActivityPharmacistBinding binding;
    private String resultInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPharmacistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");

        binding.butSearchMedicinesForCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacistActivity.this,
                        SearchMedicinesForCheckActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                intent.putExtra("ipAddress", ipAddress);
                startActivity(intent);
            }
        });

        binding.butFixDataInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                    loginGet, passwordGet);
                            System.out.println("DataBase start");
                            PreparedStatement statement = connection.prepareStatement(
                                    "CALL remove_overdue_medicine();");
                            statement.executeUpdate();
                            statement.close();
                            connection.close();
                            resultInsert = "Успешно";
                        } catch (Exception e) {
                            resultInsert = "Ошибка";
                            e.printStackTrace();
                        }
                    }
                });
                gfgThread.start();
                try {
                    gfgThread.join();
                    Toast.makeText(PharmacistActivity.this, resultInsert,
                            Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.butUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacistActivity.this,
                        UpdateStatusMedicinesActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                intent.putExtra("ipAddress", ipAddress);
                startActivity(intent);
            }
        });
    }
}