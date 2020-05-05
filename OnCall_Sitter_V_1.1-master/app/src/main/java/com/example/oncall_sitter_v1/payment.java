package com.example.oncall_sitter_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.craftman.cardform.CardForm;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        TextView paymentAmount = (TextView) findViewById(R.id.payment_amount);
        paymentAmount.setText("Free ($0.00)");

        Button btn = (Button) findViewById(R.id.btn_pay);
        btn.setText("Sign Up");
        btn.setBackgroundResource(R.drawable.our_button);
        btn.setTextSize(18);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                done(this);
            }
        });

    }

    public void done(View.OnClickListener view){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.term);
        dialog.setTitle("Registration Successful");
        dialog.show();
    }

    public void search(android.view.View view){
        Intent intent = new Intent(this, SearchParameters.class);
        startActivity(intent);
    }
}
