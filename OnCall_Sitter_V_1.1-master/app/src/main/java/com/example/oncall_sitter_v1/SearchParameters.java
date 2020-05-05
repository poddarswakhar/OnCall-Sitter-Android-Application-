package com.example.oncall_sitter_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SearchParameters extends AppCompatActivity {
    public static TextView date1;
    public static TextView g;
    boolean timing = false;
    boolean d = false;
    boolean c = false;
    boolean l = false;
    TextView duration;
    String durationString;
    String durationInt;
    DrawerLayout adrawerLayout;
    ActionBarDrawerToggle aToggle;
//    Spinner spinnerCategory;
//    Spinner spinnerLocation;
//    String  categoryString;
//    String locationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parameters);
        date1 = (TextView) findViewById(R.id.date);
        g = (TextView) findViewById(R.id.textView3);

        //Drawer
        adrawerLayout = (DrawerLayout) findViewById(R.id.drawerId);
        aToggle = new ActionBarDrawerToggle(this, adrawerLayout,R.string.open, R.string.close);
        adrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
//        spinnerCategory = (Spinner) findViewById(R.id.category);
//        spinnerLocation = (Spinner) findViewById(R.id.location);
//        categoryString = spinnerCategory.getSelectedItem().toString();
//        locationString = spinnerLocation.getSelectedItem().toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void showDatePickerDialog2(View v) {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void stime(android.view.View view) throws Exception {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                TextView a = (TextView) findViewById(R.id.startTime);
                a.setText(hourOfDay + ":" + minutes);
                setDur();
            }
        }, 0, 0, false);

        timePickerDialog.show();
    }

    public void etime(android.view.View view) throws Exception {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                TextView a = (TextView) findViewById(R.id.endTime);
                a.setText(hourOfDay + ":" + minutes);
                setDur();
            }
        }, 0, 0, false);

        timePickerDialog.show();
    }

    public void setDur() {
        TextView st = (TextView) findViewById(R.id.startTime);
        TextView et = (TextView) findViewById(R.id.endTime);

        String a = st.getText().toString();
        String b = et.getText().toString();

        if (b.equals("Pick Ending Time")) {
            TextView d = (TextView) findViewById(R.id.duration);
            d.setText("--:--");
        } else {
            int startHour = Integer.parseInt(a.substring(0, a.indexOf(':')));
            int endHour = Integer.parseInt(b.substring(0, b.indexOf(':')));

            int startMin = Integer.parseInt(a.substring(a.indexOf(':') + 1, a.length()));
            int endMin = Integer.parseInt(b.substring(b.indexOf(':') + 1, b.length()));

            int hours = endHour - startHour;
            int min = Math.abs(endMin - startMin);

            if (hours < 0) {
                timing = false;
                TextView asd = (TextView) findViewById(R.id.textView5);
                Toast.makeText(this, "End Hour Must Be Later Than Start Hour", Toast.LENGTH_LONG).show();
                asd.setTextColor(Color.RED);
                et.setText("Pick Ending Time");

            } else {
                String diff = Integer.toString(hours) + " hrs : " + Integer.toString(min) + " mins";
                TextView d = (TextView) findViewById(R.id.duration);
                d.setText(diff);
                timing = true;
            }
        }
    }

    public void list(View view) {

        Spinner mySpinner = (Spinner) findViewById(R.id.category);
        String cat = mySpinner.getSelectedItem().toString();
        if (cat.equals("Select a Category")) {
            c = false;
            Toast.makeText(this, "Please Select a Category", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView1);
            dat.setTextColor(Color.RED);
        } else c=true;

        mySpinner = (Spinner) findViewById(R.id.location);
        String loc = mySpinner.getSelectedItem().toString();
        if (loc.equals("Select a Location")) {
            l = false;
            Toast.makeText(this, "Please Select a Location", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView2);
            dat.setTextColor(Color.RED);
        } else l = true;

        TextView dat = (TextView) findViewById(R.id.date);
        if (dat.getText().toString().equals("Pick a Date")) {
            d = false;
            Toast.makeText(this, "Please Select a Date", Toast.LENGTH_LONG).show();
            TextView aaa = (TextView) findViewById(R.id.textView3);
            aaa.setTextColor(Color.RED);
        } else d = true;


        if (timing == false) {

            Toast.makeText(this, "Please Select Correct Times", Toast.LENGTH_LONG).show();
            TextView aaa = (TextView) findViewById(R.id.textView4);
            aaa.setTextColor(Color.RED);

            TextView aab = (TextView) findViewById(R.id.textView5);
            aab.setTextColor(Color.RED);

        }

        if (d == true && c == true && l == true && timing == true) {
            duration = (TextView) findViewById(R.id.duration);
            durationString = duration.getText().toString();
            String[] arrOfStr = durationString.split(":");
            String hourString = arrOfStr[0].replaceAll("[^0-9]", "");
            String minString = arrOfStr[1].replaceAll("[^0-9]", "");
            SelectedAnswer.setHours(hourString);
            SelectedAnswer.setMins(minString);

            //Category and location
            Intent intentSearching = new Intent(this, sitterListView.class);
            intentSearching.putExtra("SitterCategory", cat);
            intentSearching.putExtra("SitterLocation", loc + ", British Columbia");
            intentSearching.putExtra("hours", hourString);
            intentSearching.putExtra("minutes", minString);
            startActivity(intentSearching);


        }
    }
    public static class SelectedAnswer {
        public static String hours;
        public static String mins;

        public static String getHours() {
            return hours;
        }
        public static void setHours(String answer) {
            hours = answer;
        }
        public static String getMins() {
            return mins;
        }
        public static void setMins(String answer) {
            mins = answer;
        }
    }


}


