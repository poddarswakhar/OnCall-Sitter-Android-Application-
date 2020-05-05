package com.example.oncall_sitter_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo = (ImageView)findViewById(R.id.logo);
        TextView one = (TextView) findViewById(R.id.textView1);
        Button two = (Button) findViewById(R.id.primary_user);
        Button three = (Button) findViewById(R.id.secondary_user);
        ObjectAnimator animation = ObjectAnimator.ofFloat(logo,"alpha",0,1);
        animation.setDuration(3000);
        animation.start();
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(one,"alpha",0,1);
        animation2.setDuration(5000);
        animation2.start();
        ObjectAnimator animation3 = ObjectAnimator.ofFloat(two,"alpha",0,1);
        animation3.setDuration(7000);
        animation3.start();
        ObjectAnimator animation4 = ObjectAnimator.ofFloat(three,"alpha",0,1);
        animation4.setDuration(7000);
        animation4.start();
    }

    public void puser(android.view.View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
