package com.codepath.couple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.couple.fragments.PostsFragment;
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
    private Button btnFilterSubmit;
    private TextView etShowme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btnFilterSubmit = findViewById(R.id.btnFilterSubmit);
        ctvWomen = findViewById(R.id.ctvWomen);
        ctvMen = findViewById(R.id.ctvMen);
        etShowme = findViewById(R.id.etShowme);
        ctvEveryone = findViewById(R.id.ctvEveryone);

        btnFilterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //queryPost();
                //goMainActivity();
                Intent i = new Intent(FilterActivity.this, MainActivity.class);
                i.putExtra("gender", getGender());
                setResult(RESULT_OK, i);
                finish();
            }
        });

        ctvWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvWomen.toggle();
                if (ctvWomen.isChecked()) {
                    //   choice=1;
                    Toast.makeText(FilterActivity.this, "Checked Women", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ctvMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvMen.toggle();
                if (ctvMen.isChecked()) {
                    Toast.makeText(FilterActivity.this, "Checked Men", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ctvEveryone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctvEveryone.toggle();
                if (ctvEveryone.isChecked()) {
                    Toast.makeText(FilterActivity.this, "Checked Everyone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getGender() {
        if (ctvWomen.isChecked()) {
            return "woman";
        }
        if (ctvMen.isChecked()) {
            return "man";
        }
        return "everyone";
    }
}