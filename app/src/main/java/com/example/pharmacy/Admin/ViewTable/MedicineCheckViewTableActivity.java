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
import com.example.pharmacy.databinding.ActivityDrugStoreViewTableBinding;
import com.example.pharmacy.databinding.ActivityMedicineCheckViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicineCheckViewTableActivity extends AppCompatActivity {
    private ActivityMedicineCheckViewTableBinding binding;
    private List<String> id = new ArrayList<>();
    private List<Timestamp> checkCreateTime = new ArrayList<>();
    private List<Float > totalCost = new ArrayList<>();
    private List<Integer > storeId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineCheckViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM medicine_check ORDER BY check_create_time ASC;");
                    while (resultSet.next()) {
                        id.add(resultSet.getString("id"));
                        checkCreateTime.add(Timestamp.valueOf(resultSet.getString("check_create_time")));
                        totalCost.add(Float.parseFloat(resultSet.getString("total_cost")));
                        storeId.add(Integer.parseInt(resultSet.getString("store_id")));
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


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutMedicineCheckViewTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView checkCreateTimeTextViewName = new TextView(this);
            TextView totalCostTextViewName = new TextView(this);
            TextView storeIdTextViewName = new TextView(this);



            idTextViewName.setText("id");
            checkCreateTimeTextViewName.setText("check_create_time");
            totalCostTextViewName.setText("total_cost");
            storeIdTextViewName.setText("store_id");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            checkCreateTimeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            totalCostTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            storeIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.BLACK);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            checkCreateTimeTextViewName.setTextColor(Color.BLACK);;
            checkCreateTimeTextViewName.setTextSize(18);
            checkCreateTimeTextViewName.setTypeface(null, Typeface.BOLD);
            checkCreateTimeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            totalCostTextViewName.setTextColor(Color.BLACK);;
            totalCostTextViewName.setTextSize(18);
            totalCostTextViewName.setTypeface(null, Typeface.BOLD);
            totalCostTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            storeIdTextViewName.setTextColor(Color.BLACK);;
            storeIdTextViewName.setTextSize(18);
            storeIdTextViewName.setTypeface(null, Typeface.BOLD);
            storeIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) checkCreateTimeTextViewName, 1);
            tableRow.addView((View) totalCostTextViewName, 2);
            tableRow.addView((View) storeIdTextViewName, 3);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView checkCreateTimeTextView = new TextView(this);
                TextView totalCostTextView = new TextView(this);
                TextView storeIdTextView = new TextView(this);

                idTextView.setText(id.get(i).toString());
                checkCreateTimeTextView.setText(checkCreateTime.get(i).toString());
                totalCostTextView.setText(totalCost.get(i).toString());
                storeIdTextView.setText(storeId.get(i).toString());

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                checkCreateTimeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                totalCostTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                storeIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idTextView.setTextColor(Color.BLACK);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                checkCreateTimeTextView.setTextColor(Color.BLACK);;
                checkCreateTimeTextView.setTextSize(18);
                checkCreateTimeTextView.setTypeface(null, Typeface.BOLD);
                checkCreateTimeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                totalCostTextView.setTextColor(Color.BLACK);;
                totalCostTextView.setTextSize(18);
                totalCostTextView.setTypeface(null, Typeface.BOLD);
                totalCostTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                storeIdTextView.setTextColor(Color.BLACK);;
                storeIdTextView.setTextSize(18);
                storeIdTextView.setTypeface(null, Typeface.BOLD);
                storeIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) checkCreateTimeTextView, 1);
                tableRow_all.addView((View) totalCostTextView, 2);
                tableRow_all.addView((View) storeIdTextView, 3);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}