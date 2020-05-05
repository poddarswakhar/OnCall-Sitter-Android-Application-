package com.example.oncall_sitter_v1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

public class secondLast extends AppCompatActivity {
    TextView sitterNameSL;
    TextView sitterRateSL;
    DrawerLayout adrawerLayout;
    ActionBarDrawerToggle aToggle;
    TextView total;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_last);

        adrawerLayout = (DrawerLayout) findViewById(R.id.drawerId);
        aToggle = new ActionBarDrawerToggle(this, adrawerLayout,R.string.open, R.string.close);
        adrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sitterNameSL = (TextView) findViewById(R.id.sitterName);
        sitterRateSL = (TextView) findViewById(R.id.Rate);
        total = (TextView) findViewById(R.id.totalCost);
        Intent intent = getIntent();
        String sitterNameString = intent.getStringExtra("sitterName");
        String sitterRateStrng = intent.getStringExtra("sitterRate");

        String intRate = intent.getStringExtra("rateInt");
        int intRateInt = Integer.parseInt(intRate);

        String sitterHourString = SearchParameters.SelectedAnswer.getHours();
        int sitterHourStringInt = Integer.parseInt(sitterHourString);

        String sitterMinString = SearchParameters.SelectedAnswer.getMins();
        double sitterMinStringInt = Integer.parseInt(sitterMinString);

        int totalHours =  intRateInt * sitterHourStringInt;
        int divider = 60;
        double totalMins = intRateInt * (sitterMinStringInt/divider);
        double totalAmount = totalHours + totalMins;


        String totalAmountString = df2.format(totalAmount);

        sitterNameSL.setText(sitterNameString);
        sitterRateSL.setText(sitterRateStrng);
        total.setText("$ " +totalAmountString);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void done(android.view.View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_demo);
        dialog.setTitle("Payment");
        dialog.show();
    }

    public void cancel(android.view.View view) {
        Intent intent = new Intent(this, SearchParameters.class);
        startActivity(intent);
    }

    public void proc(android.view.View view) {
        Intent intent = new Intent(this, LastPage.class);
        startActivity(intent);
    }
}
