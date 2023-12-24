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
import com.example.pharmacy.databinding.ActivityMedicineStatisticViewTableBinding;
import com.example.pharmacy.databinding.ActivityMedicineUnitViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicineUnitViewTableActivity extends AppCompatActivity {
    private ActivityMedicineUnitViewTableBinding binding;
    private List<Integer> id = new ArrayList<>();
    private List<String> check_id = new ArrayList<>();
    private List<Integer> medicineCode = new ArrayList<>();
    private List<Integer> medicineCount = new ArrayList<>();
    private List<Float> totalCost = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineUnitViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM medicine_unit ORDER BY id ASC, check_id ASC;");
                    while (resultSet.next()) {
                        id.add(Integer.parseInt(resultSet.getString("id")));
                        check_id.add(resultSet.getString("check_id"));
                        medicineCode.add(Integer.parseInt(resultSet.getString("medicine_code")));
                        medicineCount.add(Integer.parseInt(resultSet.getString("medicine_count")));
                        totalCost.add(Float.parseFloat(resultSet.getString("total_cost")));
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


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutMedicineUnitTableView.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView checkIdTimeTextViewName = new TextView(this);
            TextView medicineCodeTextViewName = new TextView(this);
            TextView medicineCountTextViewName = new TextView(this);
            TextView totalCostTextViewName = new TextView(this);


            idTextViewName.setText("id");
            checkIdTimeTextViewName.setText("check_id");
            medicineCodeTextViewName.setText("medicine_code");
            medicineCountTextViewName.setText("medicine_count");
            totalCostTextViewName.setText("total_cost");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            checkIdTimeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            medicineCodeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            medicineCountTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            totalCostTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.BLACK);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            checkIdTimeTextViewName.setTextColor(Color.BLACK);;
            checkIdTimeTextViewName.setTextSize(18);
            checkIdTimeTextViewName.setTypeface(null, Typeface.BOLD);
            checkIdTimeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            medicineCodeTextViewName.setTextColor(Color.BLACK);;
            medicineCodeTextViewName.setTextSize(18);
            medicineCodeTextViewName.setTypeface(null, Typeface.BOLD);
            medicineCodeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            medicineCountTextViewName.setTextColor(Color.BLACK);;
            medicineCountTextViewName.setTextSize(18);
            medicineCountTextViewName.setTypeface(null, Typeface.BOLD);
            medicineCountTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            totalCostTextViewName.setTextColor(Color.BLACK);;
            totalCostTextViewName.setTextSize(18);
            totalCostTextViewName.setTypeface(null, Typeface.BOLD);
            totalCostTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) checkIdTimeTextViewName, 1);
            tableRow.addView((View) medicineCodeTextViewName, 2);
            tableRow.addView((View) medicineCountTextViewName, 3);
            tableRow.addView((View) totalCostTextViewName, 4);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView checkIdTimeTextView = new TextView(this);
                TextView medicineCodeTextView = new TextView(this);
                TextView medicineCountTextView = new TextView(this);
                TextView totalCostTextView = new TextView(this);

                idTextView.setText(id.get(i).toString());
                checkIdTimeTextView.setText(check_id.get(i));
                medicineCodeTextView.setText(medicineCode.get(i).toString());
                medicineCountTextView.setText(medicineCount.get(i).toString());
                totalCostTextView.setText(totalCost.get(i).toString());

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                checkIdTimeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                medicineCodeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                medicineCountTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                totalCostTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idTextView.setTextColor(Color.BLACK);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                checkIdTimeTextView.setTextColor(Color.BLACK);;
                checkIdTimeTextView.setTextSize(18);
                checkIdTimeTextView.setTypeface(null, Typeface.BOLD);
                checkIdTimeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                medicineCodeTextView.setTextColor(Color.BLACK);;
                medicineCodeTextView.setTextSize(18);
                medicineCodeTextView.setTypeface(null, Typeface.BOLD);
                medicineCodeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                medicineCountTextView.setTextColor(Color.BLACK);;
                medicineCountTextView.setTextSize(18);
                medicineCountTextView.setTypeface(null, Typeface.BOLD);
                medicineCountTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                totalCostTextView.setTextColor(Color.BLACK);;
                totalCostTextView.setTextSize(18);
                totalCostTextView.setTypeface(null, Typeface.BOLD);
                totalCostTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) checkIdTimeTextView, 1);
                tableRow_all.addView((View) medicineCodeTextView, 2);
                tableRow_all.addView((View) medicineCountTextView, 3);
                tableRow_all.addView((View) totalCostTextView, 4);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}