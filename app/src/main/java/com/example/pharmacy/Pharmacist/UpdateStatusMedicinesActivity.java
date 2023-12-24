package com.example.pharmacy.Pharmacist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.vcn.VcnGatewayConnectionConfig;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.Admin.CRUD.InsertAdminActivity;
import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivitySearchMedicinesForCheckBinding;
import com.example.pharmacy.databinding.ActivityUpdateStatusMedicinesBinding;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpdateStatusMedicinesActivity extends AppCompatActivity {

    private ActivityUpdateStatusMedicinesBinding binding;
    private String resultInsert;
    private List<Integer> id = new ArrayList<>();
    private List<Integer> vendorCode = new ArrayList<>();
    private List<Integer> medicineCount = new ArrayList<>();
    private List<Float> unitPrice = new ArrayList<>();
    private List<Date> dateOfManufacture = new ArrayList<>();
    private List<String> arriveDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateStatusMedicinesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");


        binding.butUpdateStatusMedicines.setOnClickListener(new View.OnClickListener() {
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
                                    "CALL change_medicine_delivery_status(?, 'Прибыл в аптеку');");
                            statement.setInt(1, Integer.parseInt(binding.editTextUpdateStatusMedicinesIdStoreMedicines.getText().toString()));
                            statement.executeUpdate();
                            statement = connection.prepareStatement("SELECT * FROM arrived_medicine ORDER BY id ASC;");
                            ResultSet resultSet = statement.executeQuery();
                            while (resultSet.next()) {
                                id.add(Integer.parseInt(resultSet.getString("id")));
                                vendorCode.add(Integer.parseInt(resultSet.getString("vendor_code")));
                                medicineCount.add(Integer.parseInt(resultSet.getString("medicine_count")));
                                unitPrice.add(Float.parseFloat(resultSet.getString("unit_price")));
                                dateOfManufacture.add(java.sql.Date.valueOf(resultSet.getString("date_of_manufacture")));
                                arriveDate.add(resultSet.getString("arrive_date"));
                            }
                            resultSet.close();
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
                    binding.editTextUpdateStatusMedicinesIdStoreMedicines.setText("");
                    Toast.makeText(UpdateStatusMedicinesActivity.this, resultInsert,
                            Toast.LENGTH_SHORT).show();
                    binding.butAssureUpdateDeliveryStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.constrainLayoutAddMedicinesToUpdate.setVisibility(View.GONE);
                            binding.constraintLayoutCheckTemporaryTable.setVisibility(View.VISIBLE);
                            int BOOKSHELF_ROWS = id.size();

                            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutCheckTemporaryTable.getId());
                            TableRow tableRow = new TableRow(UpdateStatusMedicinesActivity.this);
                            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                            TextView idTextViewName  = new TextView(UpdateStatusMedicinesActivity.this);
                            TextView vendorCodeTextViewName = new TextView(UpdateStatusMedicinesActivity.this);
                            TextView medicineCountTextViewName = new TextView(UpdateStatusMedicinesActivity.this);
                            TextView unitPriceTextViewName = new TextView(UpdateStatusMedicinesActivity.this);
                            TextView dateOfManufactureTextViewName = new TextView(UpdateStatusMedicinesActivity.this);
                            TextView arrivedDateTextViewName  = new TextView(UpdateStatusMedicinesActivity.this);

                            idTextViewName.setText("id");
                            vendorCodeTextViewName.setText("vendor_code");
                            medicineCountTextViewName.setText("medicine_count");
                            unitPriceTextViewName.setText("unit_price");
                            dateOfManufactureTextViewName.setText("date_of_manufacture");
                            arrivedDateTextViewName.setText("arrived_date");

                            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
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
                            unitPriceTextViewName.setLayoutParams(new TableRow.LayoutParams(
                                    300,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                            ));
                            dateOfManufactureTextViewName.setLayoutParams(new TableRow.LayoutParams(
                                    300,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                            ));
                            arrivedDateTextViewName.setLayoutParams(new TableRow.LayoutParams(
                                    300,
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    1f
                            ));

                            idTextViewName.setTextColor(Color.BLACK);;
                            idTextViewName.setTextSize(18);
                            idTextViewName.setTypeface(null, Typeface.BOLD);
                            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            vendorCodeTextViewName.setTextColor(Color.BLACK);;
                            vendorCodeTextViewName.setTextSize(18);
                            vendorCodeTextViewName.setTypeface(null, Typeface.BOLD);
                            vendorCodeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            medicineCountTextViewName.setTextColor(Color.BLACK);;
                            medicineCountTextViewName.setTextSize(18);
                            medicineCountTextViewName.setTypeface(null, Typeface.BOLD);
                            medicineCountTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            unitPriceTextViewName.setTextColor(Color.BLACK);;
                            unitPriceTextViewName.setTextSize(18);
                            unitPriceTextViewName.setTypeface(null, Typeface.BOLD);
                            unitPriceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            dateOfManufactureTextViewName.setTextColor(Color.BLACK);;
                            dateOfManufactureTextViewName.setTextSize(18);
                            dateOfManufactureTextViewName.setTypeface(null, Typeface.BOLD);
                            dateOfManufactureTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            arrivedDateTextViewName.setTextColor(Color.BLACK);;
                            arrivedDateTextViewName.setTextSize(18);
                            arrivedDateTextViewName.setTypeface(null, Typeface.BOLD);
                            arrivedDateTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            tableRow.addView((View) idTextViewName, 0);
                            tableRow.addView((View) vendorCodeTextViewName, 1);
                            tableRow.addView((View) medicineCountTextViewName, 2);
                            tableRow.addView((View) unitPriceTextViewName, 3);
                            tableRow.addView((View) dateOfManufactureTextViewName, 4);
                            tableRow.addView((View) arrivedDateTextViewName, 5);

                            tableLayout.addView(tableRow, 0);

                            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                                TableRow tableRow_all = new TableRow(UpdateStatusMedicinesActivity.this);
                                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));

                                TextView idTextView = new TextView(UpdateStatusMedicinesActivity.this);
                                TextView vendorCodeTextView = new TextView(UpdateStatusMedicinesActivity.this);
                                TextView medicineCountTextView = new TextView(UpdateStatusMedicinesActivity.this);
                                TextView unitPriceTextView = new TextView(UpdateStatusMedicinesActivity.this);
                                TextView dateOfManufactureTextView= new TextView(UpdateStatusMedicinesActivity.this);
                                TextView arrivedDateTextView = new TextView(UpdateStatusMedicinesActivity.this);

                                idTextView.setText(id.get(i).toString());
                                vendorCodeTextView.setText(vendorCode.get(i).toString());
                                medicineCountTextView.setText(medicineCount.get(i).toString());
                                unitPriceTextView.setText(unitPrice.get(i).toString());
                                dateOfManufactureTextView.setText(dateOfManufacture.get(i).toString());
                                arrivedDateTextView.setText(arriveDate.get(i).toString());

                                idTextView.setLayoutParams(new TableRow.LayoutParams(
                                        300,
                                        TableRow.LayoutParams.WRAP_CONTENT,
                                        1f

                                ));
                                medicineCountTextView.setLayoutParams(new TableRow.LayoutParams(
                                        300,
                                        TableRow.LayoutParams.WRAP_CONTENT,
                                        1f
                                ));
                                unitPriceTextView.setLayoutParams(new TableRow.LayoutParams(
                                        300,
                                        TableRow.LayoutParams.WRAP_CONTENT,
                                        1f
                                ));
                                dateOfManufactureTextView.setLayoutParams(new TableRow.LayoutParams(
                                        300,
                                        TableRow.LayoutParams.WRAP_CONTENT,
                                        1f
                                ));
                                arrivedDateTextView.setLayoutParams(new TableRow.LayoutParams(
                                        300,
                                        TableRow.LayoutParams.WRAP_CONTENT,
                                        1f
                                ));

                                idTextView.setTextColor(Color.BLACK);;
                                idTextView.setTextSize(18);
                                idTextView.setTypeface(null, Typeface.BOLD);
                                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                vendorCodeTextView.setTextColor(Color.BLACK);;
                                vendorCodeTextView.setTextSize(18);
                                vendorCodeTextView.setTypeface(null, Typeface.BOLD);
                                vendorCodeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                medicineCountTextView.setTextColor(Color.BLACK);;
                                medicineCountTextView.setTextSize(18);
                                medicineCountTextView.setTypeface(null, Typeface.BOLD);
                                medicineCountTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                unitPriceTextView.setTextColor(Color.BLACK);;
                                unitPriceTextView.setTextSize(18);
                                unitPriceTextView.setTypeface(null, Typeface.BOLD);
                                unitPriceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                dateOfManufactureTextView.setTextColor(Color.BLACK);;
                                dateOfManufactureTextView.setTextSize(18);
                                dateOfManufactureTextView.setTypeface(null, Typeface.BOLD);
                                dateOfManufactureTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                arrivedDateTextView.setTextColor(Color.BLACK);;
                                arrivedDateTextView.setTextSize(18);
                                arrivedDateTextView.setTypeface(null, Typeface.BOLD);
                                arrivedDateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                tableRow_all.addView((View) idTextView, 0);
                                tableRow_all.addView((View) vendorCodeTextView, 1);
                                tableRow_all.addView((View) medicineCountTextView, 2);
                                tableRow_all.addView((View) unitPriceTextView, 3);
                                tableRow_all.addView((View) dateOfManufactureTextView, 4);
                                tableRow_all.addView((View) arrivedDateTextView, 5);
                                tableLayout.addView(tableRow_all, i + 1);
                            }
                            binding.butEndUdateDeliveryStatus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(UpdateStatusMedicinesActivity.this,
                                            PharmacistActivity.class);
                                    intent.putExtra("login", loginGet);
                                    intent.putExtra("password", passwordGet);
                                    intent.putExtra("ipAddress", ipAddress);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}