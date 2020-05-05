package com.example.oncall_sitter_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class login extends AppCompatActivity {
    boolean e = false;
    boolean pass = true;
    boolean yes = false;
    boolean  exist = true;
    private static final String FILE_NAME = "signInDetails.txt";
    ArrayList<String> infos = new ArrayList<>();
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView a = (TextView) findViewById(R.id.signup);
        String udata="Need an Account? Sign-up For Free";
        SpannableString content = new SpannableString(udata);
        content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        a.setText(content);
    }

    public void sup(android.view.View view) throws Exception{
        final ProgressDialog progressDialog = new ProgressDialog(login.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    public void login(android.view.View view) throws Exception{
        EditText one = (EditText)findViewById(R.id.email);
        String get = one.getText().toString();
        if(get.equals("") || !get.contains("@") || !get.contains(".")){
            e = false;
            Toast.makeText(this, "Please Enter Correct Email Id", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView1);
            dat.setTextColor(Color.RED);
        }
        else e = true;

        one = (EditText)findViewById(R.id.password);
        get = one.getText().toString();
        if(get.equals("")){
            pass = false;
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView2);
            dat.setTextColor(Color.RED);
        }
        else pass = true;

        if(pass == true && e==true){
            yes = validate();
        }

        if(exist==false){
            Toast.makeText(this, "No Account Found, Sign Up Please!", Toast.LENGTH_LONG).show();
            TextView dat = (TextView) findViewById(R.id.textView1);
            dat.setText("No Account Found, Sign Up Please!");
            dat.setTextColor(Color.RED);

            dat = (TextView) findViewById(R.id.textView2);
            dat.setText("No Account Found, Sign Up Please!");
            dat.setTextColor(Color.RED);
        }

        if(yes==false && exist == true){
            TextView dat = (TextView) findViewById(R.id.textView1);
            dat.setTextColor(Color.RED);

            dat = (TextView) findViewById(R.id.textView2);
            dat.setTextColor(Color.RED);

            Toast.makeText(this, "Incorrect Email or Password", Toast.LENGTH_LONG).show();
        }

        if(yes==true && e==true && pass==true) {
            Intent intentLogin = new Intent(this, SearchParameters.class);
            startActivity(intentLogin);
        }
    }

    public boolean validate(){
        EditText one = (EditText)findViewById(R.id.email);
        String inputEmail = one.getText().toString();

        EditText two = (EditText)findViewById(R.id.password);
        String inputPassword = two.getText().toString();


        FileInputStream r = null;
        try {
            r = openFileInput(FILE_NAME);
            InputStreamReader i = new InputStreamReader(r);
            Scanner a = new Scanner(i);
            while (a.hasNextLine()){
                infos.add(a.nextLine());
            }
        }
        catch (Exception e){
            System.out.println("ASD error");
        }


        for(int i=0; i<infos.size();i++){
            if(infos.get(i).contains(inputEmail)){
                index = i;
                break;
            }
        }

        if (index==-1){
            exist = false;
            return false;
        }

        int firstComma = infos.get(index).indexOf(',',0);
        String actualEmail = infos.get(index).substring(0,firstComma);

        int secondComma = infos.get(index).indexOf(',', firstComma+1);
        String actualPassword = infos.get(index).substring(firstComma+1, secondComma);

        if((inputEmail.equals(actualEmail)) && inputPassword.equals(actualPassword)) return true;
        else return false;



    }




}
