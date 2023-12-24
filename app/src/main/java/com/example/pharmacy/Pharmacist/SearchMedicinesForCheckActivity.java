package com.example.pharmacy.Pharmacist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.databinding.ActivitySearchMedicinesForCheckBinding;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchMedicinesForCheckActivity extends AppCompatActivity {

    private ActivitySearchMedicinesForCheckBinding binding;
    private String resultInsert;
    private List<Integer> idMedicine = new ArrayList<>();
    private List<Integer> countMedicine = new ArrayList<>();
    private List<String> resultRequest = new ArrayList<>();
    private List<String> id = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> vendorCode = new ArrayList<>();
    private List<String> medicineCount = new ArrayList<>();
    private List<String> dateOfManufacture = new ArrayList<>();
    private List<String> deliveryStatusCol = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchMedicinesForCheckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");

        binding.butSearchMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.constrainLayoutMedicineViewShowInfo.setVisibility(View.VISIBLE);
                binding.tableLayoutAllData.removeAllViews();
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:postgresql://" + ipAddress + ":5432/pharmacy",
                                    loginGet, passwordGet);
                            System.out.println("DataBase start");
                            PreparedStatement statement = connection.prepareStatement("SELECT find_medicine_by_part_of_name_brief(?);");
                            statement.setString(1, binding.editTextSearchMedicine.getText().toString());
                            ResultSet resultSet = statement.executeQuery();
                            while (resultSet.next()) {
                                resultRequest.add(resultSet.getString("find_medicine_by_part_of_name_brief"));
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
                try {
                    gfgThread.join();

                    for (int i = 0; i < resultRequest.size(); i++){
                        String[] parts = resultRequest.get(i).substring(1, resultRequest.get(i).length() - 1).split(",");
                        id.add(parts[0]);
                        name.add(parts[1]);
                        vendorCode.add(parts[2]);
                        medicineCount.add(parts[3]);
                        dateOfManufacture.add(parts[4]);
                        deliveryStatusCol.add(parts[5]);
                    }

                    int BOOKSHELF_ROWS = id.size();


                    TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutAllData.getId());
                    TableRow tableRow = new TableRow(SearchMedicinesForCheckActivity.this);
                    tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    TextView idTextViewName  = new TextView(SearchMedicinesForCheckActivity.this);
                    TextView nameTextViewName = new TextView(SearchMedicinesForCheckActivity.this);
                    TextView vendorCodeTextViewName = new TextView(SearchMedicinesForCheckActivity.this);
                    TextView medicineCountTextViewName = new TextView(SearchMedicinesForCheckActivity.this);
                    TextView dateOfManufactureTextViewName = new TextView(SearchMedicinesForCheckActivity.this);
                    TextView deliveryStatusColTextViewName  = new TextView(SearchMedicinesForCheckActivity.this);

                    idTextViewName.setText("id");
                    nameTextViewName.setText("name");
                    vendorCodeTextViewName.setText("vendor_code");
                    medicineCountTextViewName.setText("medicine_count");
                    dateOfManufactureTextViewName.setText("date_of_manufacture");
                    deliveryStatusColTextViewName.setText("delivery_status_col");

                    idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            300,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f

                    ));
                    nameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            300,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f

                    ));
                    vendorCodeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            300,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f
                    ));
                    medicineCountTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            300,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f
                    ));
                    dateOfManufactureTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            350,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f
                    ));
                    deliveryStatusColTextViewName.setLayoutParams(new TableRow.LayoutParams(
                            350,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            1f
                    ));

                    idTextViewName.setTextColor(Color.BLACK);;
                    idTextViewName.setTextSize(18);
                    idTextViewName.setTypeface(null, Typeface.BOLD);
                    idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    nameTextViewName.setTextColor(Color.BLACK);;
                    nameTextViewName.setTextSize(18);
                    nameTextViewName.setTypeface(null, Typeface.BOLD);
                    nameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    vendorCodeTextViewName.setTextColor(Color.BLACK);;
                    vendorCodeTextViewName.setTextSize(18);
                    vendorCodeTextViewName.setTypeface(null, Typeface.BOLD);
                    vendorCodeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    medicineCountTextViewName.setTextColor(Color.BLACK);;
                    medicineCountTextViewName.setTextSize(18);
                    medicineCountTextViewName.setTypeface(null, Typeface.BOLD);
                    medicineCountTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    dateOfManufactureTextViewName.setTextColor(Color.BLACK);;
                    dateOfManufactureTextViewName.setTextSize(18);
                    dateOfManufactureTextViewName.setTypeface(null, Typeface.BOLD);
                    dateOfManufactureTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    deliveryStatusColTextViewName.setTextColor(Color.BLACK);;
                    deliveryStatusColTextViewName.setTextSize(18);
                    deliveryStatusColTextViewName.setTypeface(null, Typeface.BOLD);
                    deliveryStatusColTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    tableRow.addView((View) idTextViewName, 0);
                    tableRow.addView((View) nameTextViewName, 1);
                    tableRow.addView((View) vendorCodeTextViewName, 2);
                    tableRow.addView((View) medicineCountTextViewName, 3);
                    tableRow.addView((View) dateOfManufactureTextViewName, 4);
                    tableRow.addView((View) deliveryStatusColTextViewName, 5);

                    tableLayout.addView(tableRow, 0);

                    for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                        TableRow tableRow_all = new TableRow(SearchMedicinesForCheckActivity.this);
                        tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                        TextView idTextView = new TextView(SearchMedicinesForCheckActivity.this);
                        TextView nameTextView = new TextView(SearchMedicinesForCheckActivity.this);
                        TextView vendorCodeTextView = new TextView(SearchMedicinesForCheckActivity.this);
                        TextView medicineCountTextView = new TextView(SearchMedicinesForCheckActivity.this);
                        TextView dateOfManufactureTextView= new TextView(SearchMedicinesForCheckActivity.this);
                        TextView deliveryStatusColTextView = new TextView(SearchMedicinesForCheckActivity.this);

                        idTextView.setText(id.get(i));
                        nameTextView.setText(name.get(i));
                        vendorCodeTextView.setText(vendorCode.get(i));
                        medicineCountTextView.setText(medicineCount.get(i));
                        dateOfManufactureTextView.setText(dateOfManufacture.get(i));
                        deliveryStatusColTextView.setText(deliveryStatusCol.get(i));

                        idTextView.setLayoutParams(new TableRow.LayoutParams(
                                300,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f

                        ));
                        nameTextView.setLayoutParams(new TableRow.LayoutParams(
                                300,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f

                        ));
                        deliveryStatusColTextView.setLayoutParams(new TableRow.LayoutParams(
                                300,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f
                        ));
                        medicineCountTextView.setLayoutParams(new TableRow.LayoutParams(
                                300,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f
                        ));
                        dateOfManufactureTextView.setLayoutParams(new TableRow.LayoutParams(
                                350,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f
                        ));
                        deliveryStatusColTextView.setLayoutParams(new TableRow.LayoutParams(
                                350,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1f
                        ));

                        idTextView.setTextColor(Color.BLACK);;
                        idTextView.setTextSize(18);
                        idTextView.setTypeface(null, Typeface.BOLD);
                        idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        nameTextView.setTextColor(Color.BLACK);;
                        nameTextView.setTextSize(18);
                        nameTextView.setTypeface(null, Typeface.BOLD);
                        nameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        vendorCodeTextView.setTextColor(Color.BLACK);;
                        vendorCodeTextView.setTextSize(18);
                        vendorCodeTextView.setTypeface(null, Typeface.BOLD);
                        vendorCodeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        medicineCountTextView.setTextColor(Color.BLACK);;
                        medicineCountTextView.setTextSize(18);
                        medicineCountTextView.setTypeface(null, Typeface.BOLD);
                        medicineCountTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        dateOfManufactureTextView.setTextColor(Color.BLACK);;
                        dateOfManufactureTextView.setTextSize(18);
                        dateOfManufactureTextView.setTypeface(null, Typeface.BOLD);
                        dateOfManufactureTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        deliveryStatusColTextView.setTextColor(Color.BLACK);;
                        deliveryStatusColTextView.setTextSize(18);
                        deliveryStatusColTextView.setTypeface(null, Typeface.BOLD);
                        deliveryStatusColTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        tableRow_all.addView((View) idTextView, 0);
                        tableRow_all.addView((View) nameTextView, 1);
                        tableRow_all.addView((View) vendorCodeTextView, 2);
                        tableRow_all.addView((View) medicineCountTextView, 3);
                        tableRow_all.addView((View) dateOfManufactureTextView, 4);
                        tableRow_all.addView((View) deliveryStatusColTextView, 5);

                        tableLayout.addView(tableRow_all, i + 1);

                    }
                    resultRequest.clear();
                    id.clear();
                    name.clear();
                    vendorCode.clear();
                    medicineCount.clear();
                    dateOfManufacture.clear();
                    deliveryStatusCol.clear();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.butAddInCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idMedicine.size() != 0 & idMedicine.contains(binding
                        .editTextIdStoreMedicine.getText().toString())) {
                    Toast.makeText(SearchMedicinesForCheckActivity.this, "Ошибка",
                            Toast.LENGTH_SHORT).show();
                } else {
                    idMedicine.add(Integer.parseInt(binding.editTextIdStoreMedicine
                            .getText().toString()));
                    countMedicine.add(Integer.parseInt(binding.editTextCountMedicineInCheck
                            .getText().toString()));
                }
                Integer countMedicineText = idMedicine.size();
                binding.textViewCountMedecineInCheck.setText(countMedicineText.toString());
                binding.editTextIdStoreMedicine.setText("");
                binding.editTextCountMedicineInCheck.setText("");
                binding.button5.setOnClickListener(new View.OnClickListener() {
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
                                            .editTextCreateCheckStoreId.getText().toString()));
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
                            Toast.makeText(SearchMedicinesForCheckActivity.this, resultInsert,
                                    Toast.LENGTH_SHORT).show();
                            binding.constrainLayoutMedicineViewShowInfo.setVisibility(View.GONE);
                            binding.textViewCountMedecineInCheck.setText("Пусто");
                            binding.editTextSearchMedicine.setText("");
                            binding.tableLayoutAllData.removeAllViews();
                            binding.editTextCreateCheckStoreId.setText("");
                            idMedicine.clear();
                            countMedicine.clear();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}