package com.example.pharmacy.Admin.CRUD;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmacy.databinding.ActivityDeleteAdminBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class DeleteAdminActivity extends AppCompatActivity {

    private ActivityDeleteAdminBinding binding;
    private Integer searchData;
    private String resultInsert;
    private static Boolean text = false;
    public static class MyDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Важное сообщение!")
                    .setMessage("Вы точно хотите удалить сотрудника?")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            text = true;
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            text = false;
                            dialog.cancel();
                        }
                    });
            return builder.create();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        String ipAddress = intentGet.getStringExtra("ipAddress");
        String crudTable = intentGet.getStringExtra("crudTable");
        String storeId = intentGet.getStringExtra("store_id");
        if (crudTable.equals("employee")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM employee WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                                resultInsert = "Успешно";
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        MyDialogFragment myDialogFragment = new MyDialogFragment();
//                                        FragmentManager manager = getSupportFragmentManager();
//
////                                        myDialogFragment.show(manager, "myDialog");
//
//                                        FragmentTransaction transaction = manager.beginTransaction();
//                                        myDialogFragment.show(transaction, "dialog");
//                                        myDialogFragment.onCreateDialog()
//
//                                    }
//                                });

                            } catch (Exception e) {
                                resultInsert = "Ошибка";
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread gfgThread = new Thread(runnable);
                    gfgThread.start();
                    try {
                        gfgThread.join();
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("employee_store")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {;
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM employee_store WHERE store_id = ? AND employee_id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, Integer.parseInt(storeId));
                                preparedStatement.setInt(2,Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString()));
                                preparedStatement.executeUpdate();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("medicine_category")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM medicine_category WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (crudTable.equals("position_name")) {
            binding.butDeleteDataInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchData = Integer.parseInt(binding.editTextDeleteSearchDataInformation.getText().toString());
                    Thread gfgThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DriverManager.getConnection(
                                        "jdbc:postgresql://" + ipAddress +
                                                ":5432/pharmacy", loginGet, passwordGet);
                                PreparedStatement preparedStatement = connection.prepareStatement(
                                        "DELETE FROM position_name WHERE id = ?;");
                                System.out.println("DataBase start");
                                preparedStatement.setInt(1, searchData);
                                preparedStatement.executeUpdate();
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
                        Toast.makeText(DeleteAdminActivity.this, resultInsert,
                                Toast.LENGTH_LONG).show();
                        binding.editTextDeleteSearchDataInformation.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}