package com.codepath.couple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class FilterActivity extends AppCompatActivity {

    public static final String TAG = "FilterActivity";


    private CheckedTextView ctvWomen;
    private CheckedTextView ctvMen;
    private CheckedTextView ctvEveryone;

    private TextView etShowme;
   private Button btnFilterSubmit;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_filter);


        ctvWomen = findViewById(R.id.ctvWomen);
        ctvMen = findViewById(R.id.ctvMen);
        etShowme =findViewById(R.id.etShowme);
        btnFilterSubmit=findViewById(R.id.btnFilterSubmit);
        ctvEveryone=findViewById(R.id.ctvEveryone);

        btnFilterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Gender");
                query.whereEqualTo("Gender", "man");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> query, ParseException e) {
                        if (e == null) {

                            Log.i(TAG, "YAY");
                            Log.d("score", "Retrieved " + query.size() + " scores");
                        } else {
                            Log.i(TAG, "no");

                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });

                goMainActivity();
            }



        });


        ctvWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvWomen.toggle();
                if (ctvWomen.isChecked()) {
                    Toast.makeText(FilterActivity.this, "Checked Women", Toast.LENGTH_SHORT).show();


                }
            }



        });




        ctvMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvMen.toggle();
                if(ctvMen.isChecked()){
                    Toast.makeText(FilterActivity.this, "Checked Men", Toast.LENGTH_SHORT).show();


                }
            }
        });

        ctvEveryone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvEveryone.toggle();
                if(ctvEveryone.isChecked()){
                    Toast.makeText(FilterActivity.this, "Checked Everyone", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void goMainActivity() {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }


}
