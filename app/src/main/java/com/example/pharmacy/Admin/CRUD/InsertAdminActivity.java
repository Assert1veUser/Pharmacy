package com.example.pharmacy.Admin.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacy.databinding.ActivityInsertAdminBinding;
import com.google.android.material.chip.Chip;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InsertAdminActivity extends AppCompatActivity {

    private ActivityInsertAdminBinding binding;
    private String chipCheck;
    private List<Integer> idMedicine = new ArrayList<>();
    private List<Integer> countMedicine = new ArrayList<>();
    private String resultInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String crudTable = intentGet.getStringExtra("crudTable");
        if (crudTable.equals("employee")) {
            binding.constraintLayoutInsertEmployee.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
                                        Locale.ENGLISH);
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO employee (name, surname, " + "patronymic, " +
                                                "email, phone_number, experience, " + "birth_date) " +
                                                "VALUES\n" + "(?, ?, ?, ?, ?, ?, ?);");
                                System.out.println("DataBase start");
                                preparedStatement.setString(1, binding
                                        .editTextInsertEmployeeName.getText().toString());
                                preparedStatement.setString(2, binding
                                        .editTextInsertEmployeeSurname.getText().toString());
                                preparedStatement.setString(3, binding
                                        .editTextInsertEmployeePatronymic.getText().toString());
                                preparedStatement.setString(4, binding
                                        .editTextInsertEmployeeEmail.getText().toString());
                                preparedStatement.setString(5, binding
                                        .editTextInsertEmployeePhoneNumber.getText().toString());
                                preparedStatement.setInt(6, Integer.parseInt(binding
                                        .editTextInsertEmployeeExperience.getText().toString()));
                                preparedStatement.setDate(7, java.sql.Date.valueOf(binding
                                        .editTextInsertEmployeeBirthDate.getText().toString()));
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
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                        binding.editTextInsertEmployeeName.setText("");
                        binding.editTextInsertEmployeeSurname.setText("");
                        binding.editTextInsertEmployeePatronymic.setText("");
                        binding.editTextInsertEmployeeEmail.setText("");
                        binding.editTextInsertEmployeePhoneNumber.setText("");
                        binding.editTextInsertEmployeeExperience.setText("");
                        binding.editTextInsertEmployeeBirthDate.setText("");
                        System.out.println("Yes");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("employee_store")) {
            binding.constraintLayoutInsertEmployeeStore.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO employee_store (store_id, " +
                                                "employee_id, salary, hire_date, position_id) " +
                                                "VALUES(?, ?, ?, ?, ?);");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(binding
                                        .editTextInsertEmploeeStoreStoreId.getText().toString()));
                                preparedStatement.setInt(2, Integer.parseInt(binding
                                        .editTextInsertEmployeeStoreEmployeeId.getText().toString()));
                                preparedStatement.setFloat(3, Float.parseFloat(binding
                                        .editTextInsertEmployeeStoreSalary.getText().toString()));
                                preparedStatement.setDate(4, java.sql.Date.valueOf(binding
                                        .editTextInsertEmployeeStoreHireDate.getText().toString()));
                                preparedStatement.setInt(5, Integer.parseInt(binding
                                        .editTextInsertEmployeeStorePositionId.getText().toString()));
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
                        binding.editTextInsertEmploeeStoreStoreId.setText("");
                        binding.editTextInsertEmployeeStoreEmployeeId.setText("");
                        binding.editTextInsertEmployeeStoreSalary.setText("");
                        binding.editTextInsertEmployeeStoreHireDate.setText("");
                        binding.editTextInsertEmployeeStorePositionId.setText("");
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("medicine")) {
            binding.constraintLayoutInsertMedicine.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<Integer> ids = binding.chipGroupInsertMedicinePrescription
                            .getCheckedChipIds();
                    for (Integer id:ids){
                        Chip chip = binding.chipGroupInsertMedicinePrescription.findViewById(id);
                        chipCheck = chip.getText().toString();
                    }
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO medicine (vendor_code, " + "name, " +
                                                "category_id, manufacturer_country, " +
                                                "manufacturer_company, count_in_package, " +
                                                "prescription, description, expiration_date) " +
                                                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(binding
                                        .editTextInsertMedicineVendorCode.getText().toString()));
                                preparedStatement.setString(2, binding
                                        .editTextInsertMedicineName.getText().toString());
                                preparedStatement.setInt(3, Integer.parseInt(binding
                                        .editTextInsertMedicineCategoryId.getText().toString()));
                                preparedStatement.setString(4, binding
                                        .editTextInsertMedicineManufacturerCountry.getText().toString());
                                preparedStatement.setString(5, binding
                                        .editTextInsertMedicineManufacturerCompany.getText().toString());
                                preparedStatement.setInt(6, Integer.parseInt(binding
                                        .editTextInsertMedicineCountInPachage.getText().toString()));
                                if (chipCheck.equals("По рецепту")){
                                    preparedStatement.setBoolean(7, true);
                                } else if (chipCheck.equals("Без рецепта")) {
                                    preparedStatement.setBoolean(7, false);
                                }
                                preparedStatement.setString(8, binding
                                        .editTextInsertMedicineDescription.getText().toString());
                                preparedStatement.setInt(9, Integer.parseInt(binding
                                        .editTextInsertMedicineExpirationDate.getText().toString()));
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
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                        binding.editTextInsertMedicineVendorCode.setText("");
                        binding.editTextInsertMedicineName.setText("");
                        binding.editTextInsertMedicineCategoryId.setText("");
                        binding.editTextInsertMedicineManufacturerCountry.setText("");
                        binding.editTextInsertMedicineManufacturerCompany.setText("");
                        binding.editTextInsertMedicineCountInPachage.setText("");
                        binding.editTextInsertMedicineDescription.setText("");
                        binding.editTextInsertMedicineExpirationDate.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("medicine_category")) {
            binding.constraintLayoutInsertMedicineCategory.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO medicine_category (name)" + " VALUES(?);");
                                System.out.println("DataBase start");
                                preparedStatement.setString(1, binding
                                        .editTextInsertMedicineCategoryName.getText().toString());
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
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                        binding.editTextInsertMedicineCategoryName.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("medicine_check")) {
            binding.constrainLayoutInsertCheck.setVisibility(View.VISIBLE);
            binding.butInsertCheckAddInList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (idMedicine.size() != 0 & idMedicine.contains(binding
                            .editTextInsertCheckMidicineId.getText().toString())) {
                        Toast.makeText(InsertAdminActivity.this, "Ошибка",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        idMedicine.add(Integer.parseInt(binding.editTextInsertCheckMidicineId
                                .getText().toString()));
                        countMedicine.add(Integer.parseInt(binding.editTextInsertCheckMedicineCount
                                .getText().toString()));
//                        binding.editTextInsertCheckMidicineId.setText("");
//                        binding.editTextInsertCheckMedicineCount.setText("");
//                        binding.editTextInsertCheckStoreId.setText("");
                        binding.butInsert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Thread gfgThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Connection connection = DriverManager.getConnection(
                                                    "jdbc:postgresql://" + ipAddress +
                                                            ":5432/pharmacy", loginGet, passwordGet);
                                            PreparedStatement preparedStatement = connection
                                                    .prepareStatement("SELECT create_check(?, ?, ?);");
                                            System.out.println("DataBase start");
                                            Array arrayIdMadicine = connection.createArrayOf(
                                                    "INTEGER", idMedicine.toArray());
                                            Array arrayCountMadicine = connection.createArrayOf(
                                                    "INTEGER", countMedicine.toArray());
                                            preparedStatement.setArray(1, arrayIdMadicine);
                                            preparedStatement.setArray(2, arrayCountMadicine);
                                            preparedStatement.setInt(3, Integer.parseInt(binding
                                                    .editTextInsertCheckStoreId.getText().toString()));
//                                            preparedStatement.executeUpdate();
                                            ResultSet resultSet = preparedStatement.executeQuery();
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
                                    Toast.makeText(InsertAdminActivity.this, resultInsert,
                                            Toast.LENGTH_SHORT).show();
                                    binding.editTextInsertCheckMidicineId.setText("");
                                    binding.editTextInsertCheckMedicineCount.setText("");
                                    binding.editTextInsertCheckStoreId.setText("");
                                    idMedicine.clear();
                                    countMedicine.clear();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }
            });
        } else if (crudTable.equals("store_medicine")) {
            binding.constrainLayoutInsertMedicineStore.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO store_medicine (vendor_code, " +
                                                "store_id, medicine_count, unit_price, " +
                                                "date_of_manufacture, " + "delivery_status_col)" +
                                                " VALUES(?, ?, ?, ?, ?, 'Сбор на складе');");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(binding
                                        .editTextIsertMedicineSroreVendorCode.getText().toString()));
                                preparedStatement.setInt(2, Integer.parseInt(binding
                                        .editTextInsertMedicineStoreStoreId.getText().toString()));
                                preparedStatement.setInt(3, Integer.parseInt(binding
                                        .editTextInsertMidecineStoreMedicineCount.getText().toString()));
                                preparedStatement.setFloat(4, Float.parseFloat(binding
                                        .editTextInsertMidecineStoreUnitPrice.getText().toString()));
                                preparedStatement.setDate(5, java.sql.Date.valueOf(
                                        binding.editTextInsertMedicineStoreDateOfManufacture
                                                .getText().toString()));
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
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("position_name")) {
            binding.constraintLayoutInsertPositionName.setVisibility(View.VISIBLE);
            binding.butInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                        loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "INSERT INTO position_name (name)" + " VALUES(?);");
                                System.out.println("DataBase start");
                                preparedStatement.setString(1, binding
                                        .editTextInsertPostionNameName.getText().toString());
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
                        Toast.makeText(InsertAdminActivity.this, resultInsert,
                                Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}