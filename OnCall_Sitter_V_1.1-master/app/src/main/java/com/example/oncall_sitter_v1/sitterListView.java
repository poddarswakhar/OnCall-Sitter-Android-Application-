package com.example.oncall_sitter_v1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class sitterListView extends AppCompatActivity {
    ListView listView;
    String mTitle[];
    String mDescription[];
    int images[];
    //BabySitters
    String mBabySitterTitle[] = {"Pardeep", "Swakhar", "Katrina", "Harsimran","Vaibhav", "Sunny", "Harraj", "Jotveer", "Jack", "Jill"};
    String mBabySitterDescription[] = {"BabySitter", "BabySitter", "BabySitter", "BabySitter","BabySitter", "BabySitter", "BabySitter", "BabySitter", "BabySitter", "BabySitter"};
    int mbabysitterImages[]= {R.drawable.pardeep, R.drawable.swakhar, R.drawable.circle_cropped, R.drawable.simran, R.drawable.vaibhav, R.drawable.pardeep, R.drawable.circle_cropped, R.drawable.simran, R.drawable.swakhar, R.drawable.circle_cropped};
    //PetSitters
    String mPetSitterTitle[] = {"Pardeep", "Swakhar", "Katrina", "Harsimran","Vaibhav", "Sunny", "Harraj", "Jotveer", "Jack", "Jill"};
    String mPetSitterDescription[] = {"PetSitter", "PetSitter", "PetSitter", "PetSitter","PetSitter", "PetSitter", "PetSitter", "PetSitter", "PetSitter", "PetSitter"};
    int mPetsitterImages[]= {R.drawable.swakhar, R.drawable.simran, R.drawable.pardeep, R.drawable.circle_cropped, R.drawable.vaibhav, R.drawable.pardeep, R.drawable.circle_cropped, R.drawable.simran, R.drawable.swakhar, R.drawable.circle_cropped};
    //CareAid
    String mCareAidSitterTitle[] = {"Pardeep", "Swakhar", "Katrina", "Harsimran","Vaibhav", "Sunny", "Harraj", "Jotveer", "Jack", "Jill"};
    String mCareAidSitterDescription[] = {"Care Aid", "Care Aid", "Care Aid", "Care Aid","Care Aid", "Care Aid", "Care Aid", "Care Aid", "Care Aid", "Care Aid"};
    int mCareAidsitterImages[]= {R.drawable.pardeep, R.drawable.swakhar, R.drawable.circle_cropped, R.drawable.simran, R.drawable.vaibhav, R.drawable.pardeep, R.drawable.circle_cropped, R.drawable.simran, R.drawable.swakhar, R.drawable.circle_cropped};
    //Sitter location
    TextView sitterLocation;
    String sitterCategoryString;
    TextView sitterCategory;
    String sitterLocationString;
    //Drawer values
    DrawerLayout adrawerLayout;
    ActionBarDrawerToggle aToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setLogo(R.drawable.oncall_logo);
        //Drawer
        adrawerLayout = (DrawerLayout) findViewById(R.id.drawerId);
        aToggle = new ActionBarDrawerToggle(this, adrawerLayout, R.string.open, R.string.close);
        adrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //list view
        listView = findViewById(R.id.listView_id);
        sitterCategory = (TextView) findViewById(R.id.sitterCategory);
        sitterLocation = (TextView) findViewById(R.id.sitterLocation);

        Intent listIntent = getIntent();



        sitterCategoryString = listIntent.getStringExtra("SitterCategory");
        sitterLocationString = listIntent.getStringExtra("SitterLocation");

        sitterCategory.setText(sitterCategoryString);
        sitterLocation.setText(sitterLocationString);

        //to call Adapter class
        if(sitterCategoryString.contains("Baby")){
            mTitle = mBabySitterTitle;
            mDescription = mBabySitterDescription;
            images = mbabysitterImages;
        }
        else if(sitterCategoryString.contains("Pet")){
            mTitle = mPetSitterTitle;
            mDescription = mPetSitterDescription;
            images = mPetsitterImages;
        }else if(sitterCategoryString.contains("Care")){
            mTitle = mCareAidSitterTitle;
            mDescription = mCareAidSitterDescription;
            images = mCareAidsitterImages;
        }else{
            mTitle = mBabySitterTitle;
            mDescription = mBabySitterDescription;
            images = mbabysitterImages;
        }
        MyAdaptor adaptor = new MyAdaptor(this, mTitle, mDescription, images);
        listView.setAdapter(adaptor);
        //set item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i < mTitle.length; i++) {
                    if (position == i) {
                        Intent intent = new Intent(getApplicationContext(), profile.class);
                        Bundle bundle1 = intent.getExtras();
                        intent.putExtra("searchingParametersValues", bundle1);
                        //this intent put our zero index image to another activity
                        Bundle bundle = new Bundle();
                        bundle.putInt("image", images[i]);
                        intent.putExtras(bundle);
                        //now put stuff into the profile activity
                        intent.putExtra("title", mTitle[i]);
                        intent.putExtra("category", sitterCategoryString);
                        intent.putExtra("location", sitterLocationString );
                        //put your position
                        intent.putExtra("position", "" + i);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(aToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class MyAdaptor extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImg[];

        MyAdaptor(Context c, String title[], String description[], int img[]){
            super(c,R.layout.row,R.id.textView1, title );
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImg = img;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View covertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row =  layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.imageView_row);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            //now set our resources to our view
            images.setImageResource(rImg[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return row;
        }
    }
}
