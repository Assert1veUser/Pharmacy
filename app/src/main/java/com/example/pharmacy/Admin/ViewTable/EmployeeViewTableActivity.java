package com.example.pharmacy.Admin.ViewTable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pharmacy.DTO.Employee;
import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityEmployeeViewTableBinding;
import com.example.pharmacy.databinding.ActivityViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeViewTableActivity extends AppCompatActivity {

    private ActivityEmployeeViewTableBinding binding;
    private List<Integer> id = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> surname = new ArrayList<>();
    private List<String> patronymic = new ArrayList<>();
    private List<String> email = new ArrayList<>();
    private List<String> phoneNumber = new ArrayList<>();
    private List<Integer> experience = new ArrayList<>();
    private List<Date> birthDate = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeViewTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");

        Thread gfgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    Connection connection = DriverManager.getConnection(
                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                            loginGet, passwordGet);
                    Statement statement = connection.createStatement();
                    System.out.println("DataBase start");
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employee;");
                    while (resultSet.next()) {
                        id.add(Integer.parseInt(resultSet.getString("id")));
                        name.add(resultSet.getString("name"));
                        surname.add(resultSet.getString("surname"));
                        patronymic.add(resultSet.getString("patronymic"));
                        email.add(resultSet.getString("email"));
                        phoneNumber.add(resultSet.getString("phone_number"));
                        experience.add(Integer.parseInt(resultSet.getString("experience")));
                        birthDate.add(java.sql.Date.valueOf(resultSet.getString("birthDate")));
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        gfgThread.start();
    }
}