package com.example.pharmacy.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityDeleteAdminBinding;
import com.example.pharmacy.databinding.ActivityUpdateAdminBinding;

import org.postgresql.replication.PGReplicationConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteAdminActivity extends AppCompatActivity {

    private ActivityDeleteAdminBinding binding;
    private Integer searchData;
    private String resultInsert;
    private String searchDataEmployeeStore;
    private Integer searchDataEmployeeStoreStoreId;
    private Integer searchDataEmployeeStoreEmployeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String crudTable = intentGet.getStringExtra("crudTable");
        if (crudTable.equals("employee")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM employee WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("employee_store")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchDataEmployeeStore = binding.editTextDeleteSearchDataInformation.getText().toString();
                    String[] parts = searchDataEmployeeStore.split(",");
                    searchDataEmployeeStoreStoreId = Integer.parseInt(parts[0]);
                    searchDataEmployeeStoreEmployeeId = Integer.parseInt(parts[1]);
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM employee_store WHERE store_id = ? AND employee_id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchDataEmployeeStoreStoreId);
                                preparedStatement.setInt(2, searchDataEmployeeStoreEmployeeId);
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("medicine_category")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM medicine_category WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("position_name")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM position_name WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}