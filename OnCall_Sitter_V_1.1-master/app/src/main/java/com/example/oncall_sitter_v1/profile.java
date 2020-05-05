package com.example.oncall_sitter_v1;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class profile extends AppCompatActivity {
    //Profile picture and the name
    ImageView imageView;
    TextView title;
    int position;
    TextView sitterCat;
    TextView location;
    //Pie chart variable
    PieChart pieChart;
    //Drawer values
    DrawerLayout adrawerLayout;
    ActionBarDrawerToggle aToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //Drawer
        adrawerLayout = (DrawerLayout) findViewById(R.id.drawerId);
        aToggle = new ActionBarDrawerToggle(this, adrawerLayout, R.string.open, R.string.close);

        adrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //fetch and set the image and name in the profile activity
        imageView = findViewById(R.id.profileImage);
        title = findViewById(R.id.userName);
        sitterCat = (TextView) findViewById(R.id.profileCat);
        location = (TextView) findViewById(R.id.address);
        Intent intent = getIntent();
        Bundle bundle = this.getIntent().getExtras();
        int pic = bundle.getInt("image");
        String usertitle =  intent.getStringExtra("title");
        String sitterCatString = intent.getStringExtra("category");
        String sitterLocation = intent.getStringExtra("location");

        imageView.setImageResource(pic);
        title.setText(usertitle);
        location.setText(sitterLocation);
        sitterCat.setText(sitterCatString);
        //Create Pie chart
        pieChart = (PieChart) findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f, "Honest"));
        yValues.add(new PieEntry(34f, "Trustworthy"));
        yValues.add(new PieEntry(34f, "Secure"));
        yValues.add(new PieEntry(34f, "Punchual"));
        yValues.add(new PieEntry(34f, "Displained"));

        pieChart.animateY(2000, Easing.EaseInOutCubic);

        PieDataSet dataset = new PieDataSet(yValues, "Personality Tags");
        dataset.setSliceSpace(3f);
        dataset.setSelectionShift(5f);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataset);
        data.setValueTextSize(1f);
        dataset.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void selectSitterProfile(View view) {
        TextView sitterName = (TextView) findViewById(R.id.userName);
        TextView sitterRate = (TextView) findViewById(R.id.sitterRate);
        //TextView duration = (TextView) findViewById(R.id.duration);

        String sitterNameString = sitterName.getText().toString();
        String sitterRateString = sitterRate.getText().toString();
        String rate = sitterRateString.substring(1,3);
        //String durationString = duration.getText().toString();

     Intent intentSL = new Intent(this, secondLast.class);
     intentSL.putExtra("sitterName", sitterNameString);
     intentSL.putExtra("sitterRate", sitterRateString);
     intentSL.putExtra("rateInt", rate);
     //intentSL.putExtra("numberOfHours", durationString);
     startActivity(intentSL);
    }
}
