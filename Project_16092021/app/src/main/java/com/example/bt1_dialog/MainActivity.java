package com.example.bt1_dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Build;
import android.renderscript.Element;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView;

import com.example.bt1_dialog.databinding.ActivityMainBinding;

import java.security.DomainLoadStoreParameter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] types = {"Work", "Friend", "Family"};

    ActivityMainBinding activityMainBinding;
    String vt1 = "", vt2 = "";
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Spinner spin = activityMainBinding.spinner;
        spin.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);

        Date date = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        activityMainBinding.date.setText(sdf.format(date));

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        activityMainBinding.time.setText(currentTime);


        activityMainBinding.tvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] stringsTag = {"Family", "Game", "Android", "VTC", "Friend"};
                boolean[] booleans = {false, false, false, false, false};
                builder1.setTitle("Choose Tags")
                        .setMultiChoiceItems(stringsTag, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if (b) {
                                    vt1 += stringsTag[i] + ", ";
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (vt1.compareToIgnoreCase("") == 0) {
                                    activityMainBinding.tvTag.setText("Choose Tags");
                                } else {
                                    activityMainBinding.tvTag.setText(vt1);
                                    vt1 = "";
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder1.create();
                //Setting the title manually
                alert.setTitle("Choose Tags");
                alert.show();
            }
        });


        activityMainBinding.tvWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] stringsWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};
                builder2.setTitle("Choose Tags")
                        .setMultiChoiceItems(stringsWeek, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if (b) {
                                    vt2 += stringsWeek[i] + ", ";
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            //                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (vt2.compareToIgnoreCase("") == 0) {
                                    activityMainBinding.tvWeek.setText("Choose Tags");
                                } else {
                                    activityMainBinding.tvWeek.setText(vt2);
                                    vt2 = "";
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder2.create();
                //Setting the title manually
                alert.setTitle("Choose Tags");
                alert.show();
            }
        });


        activityMainBinding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();


        activityMainBinding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        activityMainBinding.time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        activityMainBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
                Toast.makeText(getBaseContext(), "Success!!!", Toast.LENGTH_LONG).show();
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuname, menu);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), types[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateDisplay() {
        activityMainBinding.date.setText(//this is the edit text where you want to show the selected date
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("/")
                        .append(mMonth + 1).append("/")
                        .append(mYear).append(""));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

}