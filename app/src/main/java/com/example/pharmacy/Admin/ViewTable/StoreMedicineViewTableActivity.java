package com.example.pharmacy.Admin.ViewTable;

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

import com.example.pharmacy.databinding.ActivityStoreMedicineViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreMedicineViewTableActivity extends AppCompatActivity {

    private ActivityStoreMedicineViewTableBinding binding;
    private List<Integer> id = new ArrayList<>();
    private List<Integer> vendorCode = new ArrayList<>();
    private List<Integer> storeId = new ArrayList<>();
    private List<Integer> medicineCount = new ArrayList<>();
    private List<Float> unitPrice = new ArrayList<>();
    private List<Date> dateOfManufacture = new ArrayList<>();
    private List<String> deliveryStatusCol = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreMedicineViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM store_medicine ORDER BY id ASC, " +
                            "vendor_code ASC;");
                    while (resultSet.next()) {
                        id.add(Integer.parseInt(resultSet.getString("id")));
                        vendorCode.add(Integer.parseInt(resultSet.getString("vendor_code")));
                        storeId.add(Integer.parseInt(resultSet.getString("store_id")));
                        medicineCount.add(Integer.parseInt(resultSet.getString("medicine_count")));
                        unitPrice.add(Float.parseFloat(resultSet.getString("unit_price")));
                        dateOfManufacture.add(java.sql.Date.valueOf(resultSet.getString("date_of_manufacture")));
                        deliveryStatusCol.add(resultSet.getString("delivery_status_col"));
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
            int BOOKSHELF_ROWS = id.size();


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutsStoreMedicineTableLayouts.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView vendorCodeTextViewName = new TextView(this);
            TextView storeIdTextViewName = new TextView(this);
            TextView medicineCountTextViewName = new TextView(this);
            TextView unitPriceTextViewName = new TextView(this);
            TextView dateOfManufactureTextViewName = new TextView(this);
            TextView deliveryStatusColTextViewName  = new TextView(this);

            idTextViewName.setText("id");
            vendorCodeTextViewName.setText("vendor_code");
            storeIdTextViewName.setText("store_id");
            medicineCountTextViewName.setText("medicine_count");
            unitPriceTextViewName.setText("unit_price");
            dateOfManufactureTextViewName.setText("date_of_manufacture");
            deliveryStatusColTextViewName.setText("delivery_status_col");

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
            storeIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
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
            deliveryStatusColTextViewName.setLayoutParams(new TableRow.LayoutParams(
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

            storeIdTextViewName.setTextColor(Color.BLACK);;
            storeIdTextViewName.setTextSize(18);
            storeIdTextViewName.setTypeface(null, Typeface.BOLD);
            storeIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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

            deliveryStatusColTextViewName.setTextColor(Color.BLACK);;
            deliveryStatusColTextViewName.setTextSize(18);
            deliveryStatusColTextViewName.setTypeface(null, Typeface.BOLD);
            deliveryStatusColTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) vendorCodeTextViewName, 1);
            tableRow.addView((View) storeIdTextViewName, 2);
            tableRow.addView((View) medicineCountTextViewName, 3);
            tableRow.addView((View) unitPriceTextViewName, 4);
            tableRow.addView((View) dateOfManufactureTextViewName, 5);
            tableRow.addView((View) deliveryStatusColTextViewName, 6);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView vendorCodeTextView = new TextView(this);
                TextView storeIdTextView = new TextView(this);
                TextView medicineCountTextView = new TextView(this);
                TextView unitPriceTextView = new TextView(this);
                TextView dateOfManufactureTextView= new TextView(this);
                TextView deliveryStatusColTextView = new TextView(this);

                idTextView.setText(id.get(i).toString());
                vendorCodeTextView.setText(vendorCode.get(i).toString());
                storeIdTextView.setText(storeId.get(i).toString());
                medicineCountTextView.setText(medicineCount.get(i).toString());
                unitPriceTextView.setText(unitPrice.get(i).toString());
                dateOfManufactureTextView.setText(dateOfManufacture.get(i).toString());
                deliveryStatusColTextView.setText(deliveryStatusCol.get(i));

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                deliveryStatusColTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                storeIdTextView.setLayoutParams(new TableRow.LayoutParams(
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
                deliveryStatusColTextView.setLayoutParams(new TableRow.LayoutParams(
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

                storeIdTextView.setTextColor(Color.BLACK);;
                storeIdTextView.setTextSize(18);
                storeIdTextView.setTypeface(null, Typeface.BOLD);
                storeIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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

                deliveryStatusColTextView.setTextColor(Color.BLACK);;
                deliveryStatusColTextView.setTextSize(18);
                deliveryStatusColTextView.setTypeface(null, Typeface.BOLD);
                deliveryStatusColTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) vendorCodeTextView, 1);
                tableRow_all.addView((View) storeIdTextView, 2);
                tableRow_all.addView((View) medicineCountTextView, 3);
                tableRow_all.addView((View) unitPriceTextView, 4);
                tableRow_all.addView((View) dateOfManufactureTextView, 5);
                tableRow_all.addView((View) deliveryStatusColTextView, 6);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}