package com.example.pharmacy.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmacy.databinding.ActivityNewEmployeeActivtyBinding;
import com.google.android.material.chip.Chip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class NewEmployeeActivty extends AppCompatActivity {

    private ActivityNewEmployeeActivtyBinding binding;
    private String chipCheck;
    private String loginNewEmployee;
    private String passwordNewEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewEmployeeActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        binding.butAddNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ids = binding.chipGroupNewEmployee.getCheckedChipIds();
                for (Integer id:ids){
                    Chip chip = binding.chipGroupNewEmployee.findViewById(id);
                    chipCheck = chip.getText().toString();
                }
                if (chipCheck.equals("Админ")){
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try  {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" +
                                                ipAddress + ":5432/pharmacy",
                                        loginGet,
                                        passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement("SELECT create_admin(?, ?);");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(binding.editTextStoreIdNewEmployee.getText().toString()));
                                preparedStatement.setInt(2, Integer.parseInt(binding.editTextEmployeeIdNewEmployee.getText().toString()));
                                String newEmployeeData = "";
                                ResultSet resultSet = preparedStatement.executeQuery();
                                while (resultSet.next()) {
                                    newEmployeeData = resultSet.getString("create_admin");
                                }
                                String log[] = newEmployeeData.split(",");
                                loginNewEmployee = log[0].substring(1);
                                passwordNewEmployee = log[1].substring(0, log[1].length() - 1);
                                resultSet.close();
                                preparedStatement.close();
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    gfgThread.start();
                    try {
                        gfgThread.join();
                        binding.constraintLayoutNewEmployeeAdd.setVisibility(View.GONE);
                        binding.butAddNewEmployee.setVisibility(View.GONE);
                        binding.constraintLayoutGetLoginPassword.setVisibility(View.VISIBLE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (chipCheck.equals("Фармацевт")) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try  {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" +
                                                ipAddress + ":5432/pharmacy",
                                        loginGet,
                                        passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement("SELECT create_pharmacist(?, ?);");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(binding.editTextStoreIdNewEmployee.getText().toString()));
                                preparedStatement.setInt(2, Integer.parseInt(binding.editTextEmployeeIdNewEmployee.getText().toString()));
                                String newEmployeeData = "";
                                ResultSet resultSet = preparedStatement.executeQuery();
                                while (resultSet.next()) {
                                    newEmployeeData = resultSet.getString("create_pharmacist");
                                }
                                System.out.println(newEmployeeData);
                                String log[] = newEmployeeData.split(",");
                                loginNewEmployee = log[0].substring(1);
                                passwordNewEmployee = log[1].substring(0, log[1].length() - 1);
                                System.out.println(log[0]);
                                System.out.println(log[1]);
                                resultSet.close();
                                preparedStatement.close();
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    gfgThread.start();
                    try {
                        gfgThread.join();
                        binding.constraintLayoutNewEmployeeAdd.setVisibility(View.GONE);
                        binding.butAddNewEmployee.setVisibility(View.GONE);
                        binding.textViewLoginNewEmployee.setText(loginNewEmployee);
                        binding.textViewPasswordNewEmployee.setText(passwordNewEmployee);
                        binding.constraintLayoutGetLoginPassword.setVisibility(View.VISIBLE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(NewEmployeeActivty.this, "Ошибка", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}