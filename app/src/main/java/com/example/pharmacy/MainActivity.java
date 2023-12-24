package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pharmacy.Admin.AdminActivity;
import com.example.pharmacy.Pharmacist.PharmacistActivity;
import com.example.pharmacy.databinding.ActivityMainBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String ipAddress = "192.168.0.163";
        binding.butEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:postgresql://" +
                                            ipAddress + ":5432/pharmacy",
                                    binding.editTextLogin.getText().toString(),
                                    binding.editTextPassword.getText().toString());
                            if (firstNChars(binding.editTextLogin.getText().toString(), 2)
                                    .equals("ad")){
                                Intent intent = new Intent(MainActivity.this,
                                        AdminActivity.class);
                                intent.putExtra("login", binding.editTextLogin.getText()
                                        .toString());
                                intent.putExtra("password", binding.editTextPassword.getText()
                                        .toString());
                                intent.putExtra("ipAddress", ipAddress);
                                intent.putExtra("storeId", employeeNChars(binding
                                        .editTextLogin.getText().toString()).get(0));
                                intent.putExtra("employeeId", employeeNChars(binding
                                        .editTextLogin.getText().toString()).get(1));
                                startActivity(intent);
                            } else if (firstNChars(binding.editTextLogin.getText().toString(), 2)
                                    .equals("ph")) {
                                Intent intent = new Intent(MainActivity.this,
                                        PharmacistActivity.class);
                                intent.putExtra("login", binding.editTextLogin.getText()
                                        .toString());
                                intent.putExtra("password", binding.editTextPassword.getText()
                                        .toString());
                                intent.putExtra("ipAddress", ipAddress);
                                startActivity(intent);
                            }
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                gfgThread.start();
            }
        });
    }
    public static String firstNChars(String str, int n) {
        if (str == null) {
            return null;
        }
        return str.length() < n ? str : str.substring(0, n);
    }
    public static List<String> employeeNChars(String str){
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[^0-9]+", " ");
        return Arrays.asList(str.trim().split(" "));
    }
}