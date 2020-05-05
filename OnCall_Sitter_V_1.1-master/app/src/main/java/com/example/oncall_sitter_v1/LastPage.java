package com.example.oncall_sitter_v1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class LastPage extends AppCompatActivity {
    DrawerLayout adrawerLayout;
    ActionBarDrawerToggle aToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        adrawerLayout = (DrawerLayout) findViewById(R.id.drawerId);
        aToggle = new ActionBarDrawerToggle(this, adrawerLayout,R.string.open, R.string.close);
        adrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void main(android.view.View view) {
        Intent intent = new Intent(this, SearchParameters.class);
        startActivity(intent);
    }
}
