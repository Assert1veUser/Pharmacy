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

import com.example.pharmacy.R;
import com.example.pharmacy.databinding.ActivityMedicineCheckViewTableBinding;
import com.example.pharmacy.databinding.ActivityMedicineStatisticViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MedicineStatisticViewTableActivity extends AppCompatActivity {

    private ActivityMedicineStatisticViewTableBinding binding;
    private List<Integer> store_id = new ArrayList<>();
    private List<Integer> category_id = new ArrayList<>();
    private List<Float> totalIncome = new ArrayList<>();
    private List<Integer> boughtCount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineStatisticViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM medicine_statistic ORDER BY store_id ASC, " +
                            "category_id ASC;");
                    while (resultSet.next()) {
                        store_id.add(Integer.parseInt(resultSet.getString("store_id")));
                        category_id.add(Integer.parseInt(resultSet.getString("category_id")));
                        totalIncome.add(Float.parseFloat(resultSet.getString("total_income")));
                        boughtCount.add(Integer.parseInt(resultSet.getString("bought_count")));
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
            int BOOKSHELF_ROWS = store_id.size();


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutMedicineStatisticTableView.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView storeIdTextViewName  = new TextView(this);
            TextView categoryIdTimeTextViewName = new TextView(this);
            TextView totalIncomeTextViewName = new TextView(this);
            TextView boughtCountTextViewName = new TextView(this);



            storeIdTextViewName.setText("stoer_id");
            categoryIdTimeTextViewName.setText("category_id");
            totalIncomeTextViewName.setText("total_income");
            boughtCountTextViewName.setText("bought_count");

            storeIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            categoryIdTimeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            totalIncomeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            boughtCountTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            storeIdTextViewName.setTextColor(Color.BLACK);;
            storeIdTextViewName.setTextSize(18);
            storeIdTextViewName.setTypeface(null, Typeface.BOLD);
            storeIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            categoryIdTimeTextViewName.setTextColor(Color.BLACK);;
            categoryIdTimeTextViewName.setTextSize(18);
            categoryIdTimeTextViewName.setTypeface(null, Typeface.BOLD);
            categoryIdTimeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            totalIncomeTextViewName.setTextColor(Color.BLACK);;
            totalIncomeTextViewName.setTextSize(18);
            totalIncomeTextViewName.setTypeface(null, Typeface.BOLD);
            totalIncomeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            boughtCountTextViewName.setTextColor(Color.BLACK);;
            boughtCountTextViewName.setTextSize(18);
            boughtCountTextViewName.setTypeface(null, Typeface.BOLD);
            boughtCountTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) storeIdTextViewName, 0);
            tableRow.addView((View) categoryIdTimeTextViewName, 1);
            tableRow.addView((View) totalIncomeTextViewName, 2);
            tableRow.addView((View) boughtCountTextViewName, 3);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView storeIdTextView = new TextView(this);
                TextView categoryIdTextView = new TextView(this);
                TextView totalIncomeTextView = new TextView(this);
                TextView boughtCountTextView = new TextView(this);

                storeIdTextView.setText(store_id.get(i).toString());
                categoryIdTextView.setText(category_id.get(i).toString());
                totalIncomeTextView.setText(totalIncome.get(i).toString());
                boughtCountTextView.setText(boughtCount.get(i).toString());

                storeIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                categoryIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                totalIncomeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                boughtCountTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                storeIdTextView.setTextColor(Color.BLACK);;
                storeIdTextView.setTextSize(18);
                storeIdTextView.setTypeface(null, Typeface.BOLD);
                storeIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                categoryIdTextView.setTextColor(Color.BLACK);;
                categoryIdTextView.setTextSize(18);
                categoryIdTextView.setTypeface(null, Typeface.BOLD);
                categoryIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                totalIncomeTextView.setTextColor(Color.BLACK);;
                totalIncomeTextView.setTextSize(18);
                totalIncomeTextView.setTypeface(null, Typeface.BOLD);
                totalIncomeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                boughtCountTextView.setTextColor(Color.BLACK);;
                boughtCountTextView.setTextSize(18);
                boughtCountTextView.setTypeface(null, Typeface.BOLD);
                boughtCountTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) storeIdTextView, 0);
                tableRow_all.addView((View) categoryIdTextView, 1);
                tableRow_all.addView((View) totalIncomeTextView, 2);
                tableRow_all.addView((View) boughtCountTextView, 3);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}