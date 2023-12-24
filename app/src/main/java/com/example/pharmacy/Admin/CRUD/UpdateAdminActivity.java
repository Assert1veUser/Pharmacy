package com.example.pharmacy.Admin.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmacy.DTO.Employee;
import com.example.pharmacy.DTO.EmployeeStore;
import com.example.pharmacy.DTO.Medicine;
import com.example.pharmacy.DTO.MedicineCategory;
import com.example.pharmacy.DTO.PositionName;
import com.example.pharmacy.DTO.StoreMedicine;
import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityUpdateAdminBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateAdminActivity extends AppCompatActivity {

    private ActivityUpdateAdminBinding binding;
    private String resultInsert;
    private Integer searchData;
    private Integer employeeId;
    private Employee employee = new Employee();
    private EmployeeStore employeeStore = new EmployeeStore();
    private Medicine medicine = new Medicine();
    private MedicineCategory medicineCategory = new MedicineCategory();
    private PositionName positionName = new PositionName();
    private StoreMedicine storeMedicine = new StoreMedicine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);
        binding = ActivityUpdateAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String crudTable = intentGet.getStringExtra("crudTable");
        String storeId = intentGet.getStringExtra("store_id");
        if (crudTable.equals("employee")) {
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        searchData = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM employee WHERE id = ?;");
                                    System.out.println("DataBase start");
                                    preparedStatement.setInt(1, searchData);
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        employee.setName(resultSet.getString("name"));
                                        employee.setSurname(resultSet.getString("surname"));
                                        employee.setPatronymic(resultSet.getString("patronymic"));
                                        employee.setEmail(resultSet.getString("email"));
                                        employee.setPhoneNumber(resultSet.getString("phone_number"));
                                        employee.setExperience(resultSet.getInt("experience"));
                                        employee.setBirthDate(resultSet.getDate("birth_date"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();
                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdateEmployee.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdateEmployeeName.setText(employee.getName());
                            binding.editTextUpdateEmployeeSurname.setText(employee.getSurname());
                            binding.editTextUpdateEmployeePatronymic.setText(employee.getPatronymic());
                            binding.editTextUpdateEmployeeEmail.setText(employee.getEmail());
                            binding.editTextUpdateEmployeePhoneNumber.setText(employee.getPhoneNumber());
                            binding.editTextUpdateEmployeeExperience.setText(employee.getExperience().toString());
                            binding.editTextUpdateEmployeeBirthDate.setText(employee.getBirthDate().toString());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE employee SET (name, surname, " +
                                                                "patronymic, email, phone_number, " +
                                                                "experience, birth_date) = (?, ?, ?," +
                                                                "?, ?, ?, ?) WHERE id = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setString(1, binding.editTextUpdateEmployeeName.getText().toString());
                                                preparedStatement.setString(2, binding.editTextUpdateEmployeeSurname.getText().toString());
                                                preparedStatement.setString(3, binding.editTextUpdateEmployeePatronymic.getText().toString());
                                                preparedStatement.setString(4, binding.editTextUpdateEmployeeEmail.getText().toString());
                                                preparedStatement.setString(5, binding.editTextUpdateEmployeePhoneNumber.getText().toString());
                                                preparedStatement.setInt(6, Integer.parseInt(binding.editTextUpdateEmployeeExperience.getText().toString()));
                                                preparedStatement.setDate(7, java.sql.Date.valueOf(binding.editTextUpdateEmployeeBirthDate.getText().toString()));
                                                preparedStatement.setInt(8, searchData);
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
                                        binding.constraintLayoutUpdateEmployee.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdateEmployeeName.setText("");
                                        binding.editTextUpdateEmployeeSurname.setText("");
                                        binding.editTextUpdateEmployeePatronymic.setText("");
                                        binding.editTextUpdateEmployeeEmail.setText("");
                                        binding.editTextUpdateEmployeePhoneNumber.setText("");
                                        binding.editTextUpdateEmployeeExperience.setText("");
                                        binding.editTextUpdateEmployeeBirthDate.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (crudTable.equals("employee_store")) {
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM employee_store WHERE store_id = ? AND employee_id = ?;");
                                    System.out.println("DataBase start");
                                    employeeId = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                                    preparedStatement.setInt(1, Integer.parseInt(storeId));
                                    preparedStatement.setInt(2, Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString()));
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        employeeStore.setSalary(resultSet.getFloat("salary"));
                                        employeeStore.setHireDate(resultSet.getDate("hire_date"));
                                        employeeStore.setPositionId(resultSet.getInt("position_id"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();
                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdateEmployeeStore.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdateEmployeeStoreSalary.setText(employeeStore.getSalary().toString());
                            binding.editTextUpdateEmployeeStoreHireDate.setText(employeeStore.getHireDate().toString());
                            binding.editTextUpdateEmployeeStorePostionId.setText(employeeStore.getPositionId().toString());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE employee_store SET (salary, hire_date, " +
                                                                "position_id) = (?, ?, ?) " +
                                                                "WHERE store_id = ? AND employee_id = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setFloat(1, Float.parseFloat(binding.editTextUpdateEmployeeStoreSalary.getText().toString()));
                                                preparedStatement.setDate(2, java.sql.Date.valueOf(binding.editTextUpdateEmployeeStoreHireDate.getText().toString()));
                                                preparedStatement.setInt(3, Integer.parseInt(binding.editTextUpdateEmployeeStorePostionId.getText().toString()));
                                                preparedStatement.setInt(4, Integer.parseInt(storeId));
                                                preparedStatement.setInt(5, employeeId);
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
                                        binding.constraintLayoutUpdateEmployeeStore.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdateEmployeeStoreSalary.setText("");
                                        binding.editTextUpdateEmployeeStoreHireDate.setText("");
                                        binding.editTextUpdateEmployeeStorePostionId.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (crudTable.equals("medicine")){
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        searchData = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM medicine WHERE vendor_code = ?;");
                                    System.out.println("DataBase start");
                                    preparedStatement.setInt(1, searchData);
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        medicine.setName(resultSet.getString("name"));
                                        medicine.setCategoryId(resultSet.getInt("category_id"));
                                        medicine.setManufactureCountry(resultSet.getString("manufacturer_country"));
                                        medicine.setManufactureCompany(resultSet.getString("manufacturer_company"));
                                        medicine.setCountInPackage(resultSet.getInt("count_in_package"));
                                        medicine.setPrescription(resultSet.getBoolean("prescription"));
                                        medicine.setDescription(resultSet.getString("description"));
                                        medicine.setExpirationDate(resultSet.getInt("expiration_date"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();



                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdateMedicine.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdateMedicineName.setText(medicine.getName());
                            binding.editTextUpdateMedicineCategoryId.setText(medicine.getCategoryId().toString());
                            binding.editTextUpdateMedicineManufacturerCountry.setText(medicine.getManufactureCountry());
                            binding.editTextUpdateMedicineManufacturerCompany.setText(medicine.getManufactureCompany());
                            binding.editTextUpdateMedicineCountInPackage.setText(medicine.getCountInPackage().toString());
                            if(medicine.getPrescription()){
                                binding.editTextUpdateMedicinePrescription.setText("По рецепту");
                            } else {
                                binding.editTextUpdateMedicinePrescription.setText("Без рецепта");
                            }
                            binding.editTextUpdateMedicineDescription.setText(medicine.getDescription());
                            binding.editTextUpdateMedicineExpirationDate.setText(medicine.getExpirationDate().toString());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE medicine SET (name, category_id, " +
                                                                "manufacturer_country, " +
                                                                "manufacturer_company, " +
                                                                "count_in_package, prescription, " +
                                                                "description, expiration_date) = " +
                                                                "(?, ?, ?, ?, ?, ?, ?, ?) WHERE " +
                                                                "vendor_code = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setString(1, binding.editTextUpdateMedicineName.getText().toString());
                                                preparedStatement.setInt(2, Integer.parseInt(binding.editTextUpdateMedicineCategoryId.getText().toString()));
                                                preparedStatement.setString(3, binding.editTextUpdateMedicineManufacturerCountry.getText().toString());
                                                preparedStatement.setString(4, binding.editTextUpdateMedicineManufacturerCompany.getText().toString());
                                                preparedStatement.setInt(5, Integer.parseInt(binding.editTextUpdateMedicineCountInPackage.getText().toString()));
                                                if(binding.editTextUpdateMedicinePrescription.getText().toString().equals("По рецепту")){
                                                    preparedStatement.setBoolean(6, true);
                                                } else {
                                                    preparedStatement.setBoolean(6, false);
                                                }
                                                preparedStatement.setString(7, binding.editTextUpdateMedicineDescription.getText().toString());
                                                preparedStatement.setInt(8, Integer.parseInt(binding.editTextUpdateMedicineExpirationDate.getText().toString()));
                                                preparedStatement.setInt(9, searchData);
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
                                        binding.constraintLayoutUpdateMedicine.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdateMedicineName.setText("");
                                        binding.editTextUpdateMedicineCategoryId.setText("");
                                        binding.editTextUpdateMedicineManufacturerCountry.setText("");
                                        binding.editTextUpdateMedicineManufacturerCompany.setText("");
                                        binding.editTextUpdateMedicineCountInPackage.setText("");
                                        binding.editTextUpdateMedicinePrescription.setText("");
                                        binding.editTextUpdateMedicineDescription.setText("");
                                        binding.editTextUpdateMedicineExpirationDate.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (crudTable.equals("medicine_category")) {
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        searchData = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM medicine_category WHERE id = ?;");
                                    System.out.println("DataBase start");
                                    preparedStatement.setInt(1, searchData);
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        medicineCategory.setName(resultSet.getString("name"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();

                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdateMedicineCategory.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdateMedicineCategoryName.setText(medicineCategory.getName());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE medicine_category SET name = " +
                                                                "? WHERE id = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setString(1, binding.editTextUpdateMedicineCategoryName.getText().toString());
                                                preparedStatement.setInt(2, searchData);
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
                                        binding.constraintLayoutUpdateMedicineCategory.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdateMedicineCategoryName.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (crudTable.equals("store_medicine")) {
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        searchData = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM store_medicine WHERE id = ?;");
                                    System.out.println("DataBase start");
                                    preparedStatement.setInt(1, searchData);
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        storeMedicine.setVendorCode(resultSet.getInt("vendor_code"));
                                        storeMedicine.setStoreId(resultSet.getInt("store_id"));
                                        storeMedicine.setMedicine_count(resultSet.getInt("medicine_count"));
                                        storeMedicine.setUnitPrice(resultSet.getFloat("unit_price"));
                                        storeMedicine.setDateOfManufacture(resultSet.getDate("date_of_manufacture"));
                                        storeMedicine.setDeliveryStatusCol(resultSet.getString("delivery_status_col"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();

                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdateStoreMedicine.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdateStoreMedicineVendorCode.setText(storeMedicine.getVendorCode().toString());
                            binding.editTextUpdateStoreMedicineStoreId.setText(storeMedicine.getStoreId().toString());
                            binding.editTextUpdateStoreMedicineMedicineCount.setText(storeMedicine.getMedicine_count().toString());
                            binding.editTextUpdateStoreMedicineUnitPrice.setText(storeMedicine.getUnitPrice().toString());
                            binding.editTextUpdateStoreMedicineDateOfManufacture.setText(storeMedicine.getDateOfManufacture().toString());
                            binding.editTextUpdateStoreMedicineDeliveryStatusCol.setText(storeMedicine.getDeliveryStatusCol());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE store_medicine SET (vendor_code, " +
                                                                "store_id, medicine_count, " +
                                                                "unit_price, date_of_manufacture, " +
                                                                "delivery_status_col) = " +
                                                                "(?, ?, ?, ?, ?, ?::delivery_status) WHERE id = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setInt(1, Integer.parseInt(binding.editTextUpdateStoreMedicineVendorCode.getText().toString()));
                                                preparedStatement.setInt(2, Integer.parseInt(binding.editTextUpdateStoreMedicineStoreId.getText().toString()));
                                                preparedStatement.setInt(3, Integer.parseInt(binding.editTextUpdateStoreMedicineMedicineCount.getText().toString()));
                                                preparedStatement.setFloat(4, Float.parseFloat(binding.editTextUpdateStoreMedicineUnitPrice.getText().toString()));
                                                preparedStatement.setDate(5, java.sql.Date.valueOf(binding.editTextUpdateStoreMedicineDateOfManufacture.getText().toString()));
                                                preparedStatement.setString(6, storeMedicine.getDeliveryStatusCol());
                                                preparedStatement.setInt(7, searchData);
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                PreparedStatement preparedStatement2 = connection.prepareStatement(
                                                        "CALL change_medicine_delivery_status(?, ?::delivery_status);");
                                                preparedStatement2.setInt(1, searchData);
                                                preparedStatement2.setString(2, binding.editTextUpdateStoreMedicineDeliveryStatusCol.getText().toString());
                                                preparedStatement2.executeUpdate();
                                                preparedStatement2.close();
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
                                        binding.constraintLayoutUpdateStoreMedicine.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdateStoreMedicineVendorCode.setText("");
                                        binding.editTextUpdateStoreMedicineStoreId.setText("");
                                        binding.editTextUpdateStoreMedicineMedicineCount.setText("");
                                        binding.editTextUpdateStoreMedicineUnitPrice.setText("");
                                        binding.editTextUpdateStoreMedicineDateOfManufacture.setText("");
                                        binding.editTextUpdateStoreMedicineDeliveryStatusCol.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else if (crudTable.equals("position_name")) {
            binding.butUpdateSearchDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.editTextUpdateSearchDataInformation.getText().toString().equals("")){
                        searchData = Integer.parseInt(binding.editTextUpdateSearchDataInformation.getText().toString());
                        Thread gfgThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Connection connection = DriverManager.getConnection(
                                            "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                            loginGet, passwordGet);
                                    PreparedStatement preparedStatement = connection.prepareStatement(
                                            "SELECT * FROM position_name WHERE id = ?;");
                                    System.out.println("DataBase start");
                                    preparedStatement.setInt(1, searchData);
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        positionName.setName(resultSet.getString("name"));
                                    }
                                    resultSet.close();
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
                            Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                    Toast.LENGTH_LONG).show();

                            binding.constrainLayoutSearchDataInformation.setVisibility(View.GONE);
                            binding.constraintLayoutUpdatePositionName.setVisibility(View.VISIBLE);
                            binding.butUpdate.setVisibility(View.VISIBLE);

                            binding.editTextUpdateSearchDataInformation.setText("");

                            binding.editTextUpdatePositionNameName.setText(positionName.getName());

                            binding.butUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Thread gfgThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Connection connection = DriverManager.getConnection(
                                                        "jdbc:postgresql://" + ipAddress +
                                                                ":5432/pharmacy", loginGet, passwordGet);
                                                PreparedStatement preparedStatement = connection.prepareStatement(
                                                        "UPDATE position_name SET name = " +
                                                                "? WHERE id = ?;");
                                                System.out.println("DataBase start");
                                                preparedStatement.setString(1, binding.editTextUpdatePositionNameName.getText().toString());
                                                preparedStatement.setInt(2, searchData);
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
                                        binding.constraintLayoutUpdatePositionName.setVisibility(View.GONE);
                                        binding.butUpdate.setVisibility(View.GONE);
                                        binding.constrainLayoutSearchDataInformation.setVisibility(View.VISIBLE);
                                        binding.editTextUpdatePositionNameName.setText("");
                                        Toast.makeText(UpdateAdminActivity.this, resultInsert,
                                                Toast.LENGTH_LONG).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(UpdateAdminActivity.this, "Заполните поле поиска",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}