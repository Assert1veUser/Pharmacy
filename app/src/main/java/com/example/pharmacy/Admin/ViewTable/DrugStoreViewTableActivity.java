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
import com.example.pharmacy.databinding.ActivityEmployeeViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DrugStoreViewTableActivity extends AppCompatActivity {

    private ActivityDrugStoreViewTableBinding binding;
    private List<Integer> id = new ArrayList<>();
    private List<String> address = new ArrayList<>();
    private List<String > postcode = new ArrayList<>();
    private List<String > phoneNumber = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrugStoreViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM drug_store ORDER BY id ASC;");
                    while (resultSet.next()) {
                        id.add(Integer.parseInt(resultSet.getString("id")));
                        address.add(resultSet.getString("address"));
                        postcode.add(resultSet.getString("postcode"));
                        phoneNumber.add(resultSet.getString("phone_number"));
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


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutDrugStoreViewTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView addressTextViewName = new TextView(this);
            TextView postcodeTextViewName = new TextView(this);
            TextView phoneNumberTextViewName = new TextView(this);



            idTextViewName.setText("id");
            addressTextViewName.setText("address");
            postcodeTextViewName.setText("postcode");
            phoneNumberTextViewName.setText("phone_number");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            addressTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            postcodeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            phoneNumberTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.BLACK);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            addressTextViewName.setTextColor(Color.BLACK);;
            addressTextViewName.setTextSize(18);
            addressTextViewName.setTypeface(null, Typeface.BOLD);
            addressTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            postcodeTextViewName.setTextColor(Color.BLACK);;
            postcodeTextViewName.setTextSize(18);
            postcodeTextViewName.setTypeface(null, Typeface.BOLD);
            postcodeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            phoneNumberTextViewName.setTextColor(Color.BLACK);;
            phoneNumberTextViewName.setTextSize(18);
            phoneNumberTextViewName.setTypeface(null, Typeface.BOLD);
            phoneNumberTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) addressTextViewName, 1);
            tableRow.addView((View) postcodeTextViewName, 2);
            tableRow.addView((View) phoneNumberTextViewName, 3);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView addressTextView = new TextView(this);
                TextView postcodeTextView = new TextView(this);
                TextView phoneNumberTextView = new TextView(this);

                idTextView.setText(id.get(i).toString());
                addressTextView.setText(address.get(i));
                postcodeTextView.setText(postcode.get(i));
                phoneNumberTextView.setText(phoneNumber.get(i));

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                addressTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                postcodeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                phoneNumberTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idTextView.setTextColor(Color.BLACK);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                addressTextView.setTextColor(Color.BLACK);;
                addressTextView.setTextSize(18);
                addressTextView.setTypeface(null, Typeface.BOLD);
                addressTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                postcodeTextView.setTextColor(Color.BLACK);;
                postcodeTextView.setTextSize(18);
                postcodeTextView.setTypeface(null, Typeface.BOLD);
                postcodeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                phoneNumberTextView.setTextColor(Color.BLACK);;
                phoneNumberTextView.setTextSize(18);
                phoneNumberTextView.setTypeface(null, Typeface.BOLD);
                phoneNumberTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) addressTextView, 1);
                tableRow_all.addView((View) postcodeTextView, 2);
                tableRow_all.addView((View) phoneNumberTextView, 3);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}