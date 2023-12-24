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

import com.example.pharmacy.databinding.ActivityEmployeeViewTableBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeViewTableActivity extends AppCompatActivity {

    private ActivityEmployeeViewTableBinding binding;
    private List<Integer> id = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> surname = new ArrayList<>();
    private List<String> patronymic = new ArrayList<>();
    private List<String> email = new ArrayList<>();
    private List<String> phoneNumber = new ArrayList<>();
    private List<Integer> experience = new ArrayList<>();
    private List<Date> birthDate = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeViewTableBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employee ORDER BY id ASC;");
                    while (resultSet.next()) {
                        id.add(Integer.parseInt(resultSet.getString("id")));
                        name.add(resultSet.getString("name"));
                        surname.add(resultSet.getString("surname"));
                        patronymic.add(resultSet.getString("patronymic"));
                        email.add(resultSet.getString("email"));
                        phoneNumber.add(resultSet.getString("phone_number"));
                        experience.add(Integer.parseInt(resultSet.getString("experience")));
                        birthDate.add(java.sql.Date.valueOf(resultSet.getString("birth_date")));
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


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutEmployeeViewTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView NameTextViewName = new TextView(this);
            TextView SurnameTextViewName = new TextView(this);
            TextView PatronymicTextViewName = new TextView(this);
            TextView emailTextViewName  = new TextView(this);
            TextView phoneNumberNameTextViewName = new TextView(this);
            TextView experienceTextViewName  = new TextView(this);
            TextView birthDateTextViewName  = new TextView(this);



            idTextViewName.setText("id");
            NameTextViewName.setText("name");
            SurnameTextViewName.setText("surname");
            PatronymicTextViewName.setText("patronymic");
            emailTextViewName.setText("email");
            phoneNumberNameTextViewName.setText("phone_number");
            experienceTextViewName.setText("experience");
            birthDateTextViewName.setText("birth_date");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            NameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            SurnameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            PatronymicTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            emailTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            phoneNumberNameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            experienceTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            birthDateTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.BLACK);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            NameTextViewName.setTextColor(Color.BLACK);;
            NameTextViewName.setTextSize(18);
            NameTextViewName.setTypeface(null, Typeface.BOLD);
            NameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            SurnameTextViewName.setTextColor(Color.BLACK);;
            SurnameTextViewName.setTextSize(18);
            SurnameTextViewName.setTypeface(null, Typeface.BOLD);
            SurnameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            PatronymicTextViewName.setTextColor(Color.BLACK);;
            PatronymicTextViewName.setTextSize(18);
            PatronymicTextViewName.setTypeface(null, Typeface.BOLD);
            PatronymicTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            emailTextViewName.setTextColor(Color.BLACK);;
            emailTextViewName.setTextSize(18);
            emailTextViewName.setTypeface(null, Typeface.BOLD);
            emailTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            phoneNumberNameTextViewName.setTextColor(Color.BLACK);;
            phoneNumberNameTextViewName.setTextSize(18);
            phoneNumberNameTextViewName.setTypeface(null, Typeface.BOLD);
            phoneNumberNameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            experienceTextViewName.setTextColor(Color.BLACK);;
            experienceTextViewName.setTextSize(18);
            experienceTextViewName.setTypeface(null, Typeface.BOLD);
            experienceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            birthDateTextViewName.setTextColor(Color.BLACK);;
            birthDateTextViewName.setTextSize(18);
            birthDateTextViewName.setTypeface(null, Typeface.BOLD);
            birthDateTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) NameTextViewName, 1);
            tableRow.addView((View) SurnameTextViewName, 2);
            tableRow.addView((View) PatronymicTextViewName, 3);
            tableRow.addView((View) emailTextViewName, 4);
            tableRow.addView((View) phoneNumberNameTextViewName, 5);
            tableRow.addView((View) experienceTextViewName, 6);
            tableRow.addView((View) birthDateTextViewName, 7);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView nameTextView = new TextView(this);
                TextView surnameTextView = new TextView(this);
                TextView patronymicTextView = new TextView(this);
                TextView emailTextView = new TextView(this);
                TextView phoneNumberNameTextView= new TextView(this);
                TextView experienceTextView = new TextView(this);
                TextView birthDateTextView  = new TextView(this);

                idTextView.setText(id.get(i).toString());
                nameTextView.setText(name.get(i).toString());
                surnameTextView.setText(surname.get(i).toString());
                patronymicTextView.setText(patronymic.get(i).toString());
                emailTextView.setText(email.get(i).toString());
                phoneNumberNameTextView.setText(phoneNumber.get(i).toString());
                experienceTextView.setText(experience.get(i).toString());
                birthDateTextView.setText(birthDate.get(i).toString());

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
                surnameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                patronymicTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                emailTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                phoneNumberNameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                experienceTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                birthDateTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
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

                surnameTextView.setTextColor(Color.BLACK);;
                surnameTextView.setTextSize(18);
                surnameTextView.setTypeface(null, Typeface.BOLD);
                surnameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                patronymicTextView.setTextColor(Color.BLACK);;
                patronymicTextView.setTextSize(18);
                patronymicTextView.setTypeface(null, Typeface.BOLD);
                patronymicTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                emailTextView.setTextColor(Color.BLACK);;
                emailTextView.setTextSize(18);
                emailTextView.setTypeface(null, Typeface.BOLD);
                emailTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                phoneNumberNameTextView.setTextColor(Color.BLACK);;
                phoneNumberNameTextView.setTextSize(18);
                phoneNumberNameTextView.setTypeface(null, Typeface.BOLD);
                phoneNumberNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                experienceTextView.setTextColor(Color.BLACK);;
                experienceTextView.setTextSize(18);
                experienceTextView.setTypeface(null, Typeface.BOLD);
                experienceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                birthDateTextView.setTextColor(Color.BLACK);;
                birthDateTextView.setTextSize(18);
                birthDateTextView.setTypeface(null, Typeface.BOLD);
                birthDateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) nameTextView, 1);
                tableRow_all.addView((View) surnameTextView, 2);
                tableRow_all.addView((View) patronymicTextView, 3);
                tableRow_all.addView((View) emailTextView, 4);
                tableRow_all.addView((View) phoneNumberNameTextView, 5);
                tableRow_all.addView((View) experienceTextView, 6);
                tableRow_all.addView((View) birthDateTextView, 7);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}