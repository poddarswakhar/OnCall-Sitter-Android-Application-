package com.example.oncall_sitter_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.CardForm;

import java.io.FileOutputStream;

public class signup extends AppCompatActivity {
    boolean n = false;
    boolean e = false;
    boolean p = false;
    boolean cp = false;
    boolean CheckPass = false;
    boolean a = false;
    boolean c = false;
    private static final String FILE_NAME = "signInDetails.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }

    public void payment(android.view.View view) throws Exception{
        EditText one = (EditText)findViewById(R.id.name);
        String get = one.getText().toString();
        if(get.equals("")) {
            n = false;
            Toast.makeText(this, "Please Fill Your Name:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView1);
            dat.setTextColor(Color.RED);
        }
        else n = true;

        one = (EditText)findViewById(R.id.email);
        get = one.getText().toString();
        if(get.equals("") || !get.contains("@") || !get.contains(".")){
            e = false;
            Toast.makeText(this, "Please Enter Valid Email:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView2);
            dat.setTextColor(Color.RED);
        }
        else e = true;

        one = (EditText)findViewById(R.id.password);
        get = one.getText().toString();
        if(get.equals("")){
            p = false;
            Toast.makeText(this, "Please Enter Password:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView3);
            dat.setTextColor(Color.RED);
        }
        else p = true;

        one = (EditText)findViewById(R.id.confirm_password);
        get = one.getText().toString();
        if(get.equals("")){
            cp = false;
            Toast.makeText(this, "Please Confirm Password:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView4);
            dat.setTextColor(Color.RED);
        }
        else cp = true;

        one = (EditText)findViewById(R.id.cell);
        get = one.getText().toString();
        if(get.equals("")){
            c = false;
            Toast.makeText(this, "Please Enter Cell Number:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView5);
            dat.setTextColor(Color.RED);
        }
        else c = true;

        one = (EditText)findViewById(R.id.address);
        get = one.getText().toString();
        if(get.equals("")){
            a = false;
            Toast.makeText(this, "Please Enter Your Service Address:", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView6);
            dat.setTextColor(Color.RED);
        }
        else a = true;


        if(a==true && e==true && c== true && n==true && p==true && cp==true && passCheck()==true) {
            write();
            final ProgressDialog progressDialog = new ProgressDialog(signup.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading");
            //progressDialog.show();
            Intent intent = new Intent(this, payment.class);
            startActivity(intent);
        }
    }

    public void write() throws Exception{
        String info ="";

        EditText one = (EditText)findViewById(R.id.email);
        info = one.getText().toString();

        one = (EditText)findViewById(R.id.confirm_password);
        String get = one.getText().toString();

        info = info + "," + get;

        one = (EditText)findViewById(R.id.name);
        get = one.getText().toString();

        info = info + "," + get;

        one = (EditText)findViewById(R.id.address);
        get = one.getText().toString();

        info = info + "," + get;

        one = (EditText)findViewById(R.id.cell);
        get = one.getText().toString();

        info = info + "," + get;



        FileOutputStream w = null;
        w = openFileOutput(FILE_NAME, MODE_APPEND);
        w.write(info.getBytes());
        w.write(System.getProperty("line.separator").getBytes());
        w.close();

    }


    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public boolean passCheck(){
        EditText one = (EditText)findViewById(R.id.password);
        String pass = one.getText().toString();

        EditText two = (EditText)findViewById(R.id.confirm_password);
        String conpass = two.getText().toString();

        if(pass.equals(conpass)){
            return true;
        }

        else{
            Toast.makeText(this, "Password Doesn't Match, Try Again!", Toast.LENGTH_LONG).show();
            one.setText("");
            two.setText("");
            TextView dat = (TextView) findViewById(R.id.textView4);
            dat.setTextColor(Color.RED);
            TextView dat2 = (TextView) findViewById(R.id.textView3);
            dat2.setTextColor(Color.RED);

            return false;
        }

    }
}
