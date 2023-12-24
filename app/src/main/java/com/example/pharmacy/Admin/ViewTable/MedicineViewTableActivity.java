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

import com.example.pharmacy.databinding.ActivityMedicineViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicineViewTableActivity extends AppCompatActivity {
    private ActivityMedicineViewTableBinding binding;
    private List<Integer> vendorCode = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<Integer> categoryId = new ArrayList<>();
    private List<String > manufacturerCountry = new ArrayList<>();
    private List<String > manufacturerCompany = new ArrayList<>();
    private List<Integer> countInPackage = new ArrayList<>();
    private List<Boolean> prescription = new ArrayList<>();
    private List<String > description = new ArrayList<>();
    private List<Integer> expirationDate = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM medicine ORDER BY vendor_code ASC;");
                    while (resultSet.next()) {
                        vendorCode.add(Integer.parseInt(resultSet.getString("vendor_code")));
                        name.add(resultSet.getString("name"));
                        categoryId.add(Integer.parseInt(resultSet.getString("category_id")));
                        manufacturerCountry.add(resultSet.getString("manufacturer_country"));
                        manufacturerCompany.add(resultSet.getString("manufacturer_company"));
                        countInPackage.add(Integer.parseInt(resultSet.getString("count_in_package")));
                        prescription.add(Boolean.parseBoolean(resultSet.getString("prescription")));
                        description.add(resultSet.getString("description"));
                        expirationDate.add(Integer.parseInt(resultSet.getString("expiration_date")));
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
            int BOOKSHELF_ROWS = vendorCode.size();


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutMedicineViewTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView vendorCodeTextViewName  = new TextView(this);
            TextView nameTextViewName = new TextView(this);
            TextView categoryIdTextViewName = new TextView(this);
            TextView manufacturerCountryTextViewName = new TextView(this);
            TextView manufacturerCompanyTextViewName  = new TextView(this);
            TextView countInPackageTextViewName = new TextView(this);
            TextView prescriptionTextViewName  = new TextView(this);
            TextView descriptionTextViewName  = new TextView(this);
            TextView expirationDateTextViewName  = new TextView(this);



            vendorCodeTextViewName.setText("vendore_code");
            nameTextViewName.setText("name");
            categoryIdTextViewName.setText("category_id");
            manufacturerCountryTextViewName.setText("manufacturer_country");
            manufacturerCompanyTextViewName.setText("manufacturer_company");
            countInPackageTextViewName.setText("count_in_package");
            prescriptionTextViewName.setText("prescription");
            descriptionTextViewName.setText("description");
            expirationDateTextViewName.setText("expiration_date");

            vendorCodeTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            nameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            categoryIdTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            manufacturerCountryTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            manufacturerCompanyTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            countInPackageTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            prescriptionTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            descriptionTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            expirationDateTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            vendorCodeTextViewName.setTextColor(Color.BLACK);;
            vendorCodeTextViewName.setTextSize(18);
            vendorCodeTextViewName.setTypeface(null, Typeface.BOLD);
            vendorCodeTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            nameTextViewName.setTextColor(Color.BLACK);;
            nameTextViewName.setTextSize(18);
            nameTextViewName.setTypeface(null, Typeface.BOLD);
            nameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            categoryIdTextViewName.setTextColor(Color.BLACK);;
            categoryIdTextViewName.setTextSize(18);
            categoryIdTextViewName.setTypeface(null, Typeface.BOLD);
            categoryIdTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            manufacturerCountryTextViewName.setTextColor(Color.BLACK);;
            manufacturerCountryTextViewName.setTextSize(18);
            manufacturerCountryTextViewName.setTypeface(null, Typeface.BOLD);
            manufacturerCountryTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            manufacturerCompanyTextViewName.setTextColor(Color.BLACK);;
            manufacturerCompanyTextViewName.setTextSize(18);
            manufacturerCompanyTextViewName.setTypeface(null, Typeface.BOLD);
            manufacturerCompanyTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            countInPackageTextViewName.setTextColor(Color.BLACK);;
            countInPackageTextViewName.setTextSize(18);
            countInPackageTextViewName.setTypeface(null, Typeface.BOLD);
            countInPackageTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            prescriptionTextViewName.setTextColor(Color.BLACK);;
            prescriptionTextViewName.setTextSize(18);
            prescriptionTextViewName.setTypeface(null, Typeface.BOLD);
            prescriptionTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            descriptionTextViewName.setTextColor(Color.BLACK);;
            descriptionTextViewName.setTextSize(18);
            descriptionTextViewName.setTypeface(null, Typeface.BOLD);
            descriptionTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            expirationDateTextViewName.setTextColor(Color.BLACK);;
            expirationDateTextViewName.setTextSize(18);
            expirationDateTextViewName.setTypeface(null, Typeface.BOLD);
            expirationDateTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) vendorCodeTextViewName, 0);
            tableRow.addView((View) nameTextViewName, 1);
            tableRow.addView((View) categoryIdTextViewName, 2);
            tableRow.addView((View) manufacturerCountryTextViewName, 3);
            tableRow.addView((View) manufacturerCompanyTextViewName, 4);
            tableRow.addView((View) countInPackageTextViewName, 5);
            tableRow.addView((View) prescriptionTextViewName, 6);
            tableRow.addView((View) descriptionTextViewName, 7);
            tableRow.addView((View) expirationDateTextViewName, 8);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView vendorCodeTextView = new TextView(this);
                TextView nameTextView = new TextView(this);
                TextView categoryIdTextView = new TextView(this);
                TextView manufacturerCountryTextView = new TextView(this);
                TextView manufactureCompanyTextView = new TextView(this);
                TextView countInPackageeTextView= new TextView(this);
                TextView prescriptionTextView = new TextView(this);
                TextView descriptionTextView  = new TextView(this);
                TextView expirationDateTextView  = new TextView(this);

                vendorCodeTextView.setText(vendorCode.get(i).toString());
                nameTextView.setText(name.get(i));
                categoryIdTextView.setText(categoryId.get(i).toString());
                manufacturerCountryTextView.setText(manufacturerCountry.get(i));
                manufactureCompanyTextView.setText(manufacturerCompany.get(i));
                countInPackageeTextView.setText(countInPackage.get(i).toString());
                if (prescription.get(i).toString().equals("true")){
                    prescriptionTextView.setText("По рецепту");
                }else{
                    prescriptionTextView.setText("Без рецепта");
                }
                descriptionTextView.setText(description.get(i));
                expirationDateTextView.setText(expirationDate.get(i).toString());

                vendorCodeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                nameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                categoryIdTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                manufacturerCountryTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                manufactureCompanyTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                countInPackageeTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                prescriptionTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                descriptionTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                expirationDateTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                vendorCodeTextView.setTextColor(Color.BLACK);;
                vendorCodeTextView.setTextSize(18);
                vendorCodeTextView.setTypeface(null, Typeface.BOLD);
                vendorCodeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                nameTextView.setTextColor(Color.BLACK);;
                nameTextView.setTextSize(18);
                nameTextView.setTypeface(null, Typeface.BOLD);
                nameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                categoryIdTextView.setTextColor(Color.BLACK);;
                categoryIdTextView.setTextSize(18);
                categoryIdTextView.setTypeface(null, Typeface.BOLD);
                categoryIdTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                manufacturerCountryTextView.setTextColor(Color.BLACK);;
                manufacturerCountryTextView.setTextSize(18);
                manufacturerCountryTextView.setTypeface(null, Typeface.BOLD);
                manufacturerCountryTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                manufactureCompanyTextView.setTextColor(Color.BLACK);;
                manufactureCompanyTextView.setTextSize(18);
                manufactureCompanyTextView.setTypeface(null, Typeface.BOLD);
                manufactureCompanyTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                countInPackageeTextView.setTextColor(Color.BLACK);;
                countInPackageeTextView.setTextSize(18);
                countInPackageeTextView.setTypeface(null, Typeface.BOLD);
                countInPackageeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                prescriptionTextView.setTextColor(Color.BLACK);;
                prescriptionTextView.setTextSize(18);
                prescriptionTextView.setTypeface(null, Typeface.BOLD);
                prescriptionTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                descriptionTextView.setTextColor(Color.BLACK);;
                descriptionTextView.setTextSize(18);
                descriptionTextView.setTypeface(null, Typeface.BOLD);
                descriptionTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                expirationDateTextView.setTextColor(Color.BLACK);;
                expirationDateTextView.setTextSize(18);
                expirationDateTextView.setTypeface(null, Typeface.BOLD);
                expirationDateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) vendorCodeTextView, 0);
                tableRow_all.addView((View) nameTextView, 1);
                tableRow_all.addView((View) categoryIdTextView, 2);
                tableRow_all.addView((View) manufacturerCountryTextView, 3);
                tableRow_all.addView((View) manufactureCompanyTextView, 4);
                tableRow_all.addView((View) countInPackageeTextView, 5);
                tableRow_all.addView((View) prescriptionTextView, 6);
                tableRow_all.addView((View) descriptionTextView, 7);
                tableRow_all.addView((View) expirationDateTextView, 8);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}