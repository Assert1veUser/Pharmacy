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

import com.example.pharmacy.databinding.ActivityEmployeeStoreViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeStoreViewTableActivity extends AppCompatActivity {

    private ActivityEmployeeStoreViewTableBinding binding;
    private List<Integer> storeId = new ArrayList<>();
    private List<Integer> employeeId = new ArrayList<>();
    private List<Float> salary = new ArrayList<>();
    private List<Date> hireDate = new ArrayList<>();
    private List<Integer> positionId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeStoreViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_store " +
                            "ORDER BY store_id ASC, employee_id ASC;");
                    while (resultSet.next()) {
                        storeId.add(Integer.parseInt(resultSet.getString("store_id")));
                        employeeId.add(Integer.parseInt(resultSet.getString("employee_id")));
                        salary.add(Float.parseFloat(resultSet.getString("salary")));
                        hireDate.add(java.sql.Date.valueOf(resultSet.getString("hire_date")));
                        positionId.add(Integer.parseInt(resultSet.getString("position_id")));
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
            int BOOKSHELF_ROWS = storeId.size();

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutsEmployeeStoreViewTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView storeIdTextViewName = new TextView(this);
            TextView employeeIdTextViewName = new TextView(this);
            TextView salaryTextViewName = new TextView(this);
            TextView hireDateTextViewName = new TextView(this);
            TextView positionIdTextViewName  = new TextView(this);

            storeIdTextViewName.setText("store_id");
            employeeIdTextViewName.setText("employee_id");
            salaryTextViewName.setText("salary");
            hireDateTextViewName.setText("hire_date");
            positionIdTextViewName.setText("position_id");

            storeIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            employeeIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            salaryTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            hireDateTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            positionIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            storeIdTextViewName.setTextColor(Color.BLACK);;
            storeIdTextViewName.setTextSize(18);
            storeIdTextViewName.setTypeface(null, Typeface.BOLD);
            storeIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            employeeIdTextViewName.setTextColor(Color.BLACK);;
            employeeIdTextViewName.setTextSize(18);
            employeeIdTextViewName.setTypeface(null, Typeface.BOLD);
            employeeIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            salaryTextViewName.setTextColor(Color.BLACK);;
            salaryTextViewName.setTextSize(18);
            salaryTextViewName.setTypeface(null, Typeface.BOLD);
            salaryTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            hireDateTextViewName.setTextColor(Color.BLACK);;
            hireDateTextViewName.setTextSize(18);
            hireDateTextViewName.setTypeface(null, Typeface.BOLD);
            hireDateTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            positionIdTextViewName.setTextColor(Color.BLACK);;
            positionIdTextViewName.setTextSize(18);
            positionIdTextViewName.setTypeface(null, Typeface.BOLD);
            positionIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) storeIdTextViewName, 0);
            tableRow.addView((View) employeeIdTextViewName, 1);
            tableRow.addView((View) salaryTextViewName, 2);
            tableRow.addView((View) hireDateTextViewName, 3);
            tableRow.addView((View) positionIdTextViewName, 4);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView storeIdTextView = new TextView(this);
                TextView employeeIdTextView = new TextView(this);
                TextView salaryTextView = new TextView(this);
                TextView hireDateTextView = new TextView(this);
                TextView positionIdTextView = new TextView(this);

                storeIdTextView.setText(storeId.get(i).toString());
                employeeIdTextView.setText(employeeId.get(i).toString());
                salaryTextView.setText(salary.get(i).toString());
                hireDateTextView.setText(hireDate.get(i).toString());
                positionIdTextView.setText(positionId.get(i).toString());

                storeIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                employeeIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                salaryTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                hireDateTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                positionIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                storeIdTextView.setTextColor(Color.BLACK);;
                storeIdTextView.setTextSize(18);
                storeIdTextView.setTypeface(null, Typeface.BOLD);
                storeIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                employeeIdTextView.setTextColor(Color.BLACK);;
                employeeIdTextView.setTextSize(18);
                employeeIdTextView.setTypeface(null, Typeface.BOLD);
                employeeIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                salaryTextView.setTextColor(Color.BLACK);;
                salaryTextView.setTextSize(18);
                salaryTextView.setTypeface(null, Typeface.BOLD);
                salaryTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                hireDateTextView.setTextColor(Color.BLACK);;
                hireDateTextView.setTextSize(18);
                hireDateTextView.setTypeface(null, Typeface.BOLD);
                hireDateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                positionIdTextView.setTextColor(Color.BLACK);;
                positionIdTextView.setTextSize(18);
                positionIdTextView.setTypeface(null, Typeface.BOLD);
                positionIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) storeIdTextView, 0);
                tableRow_all.addView((View) employeeIdTextView, 1);
                tableRow_all.addView((View) salaryTextView, 2);
                tableRow_all.addView((View) hireDateTextView, 3);
                tableRow_all.addView((View) positionIdTextView, 4);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}