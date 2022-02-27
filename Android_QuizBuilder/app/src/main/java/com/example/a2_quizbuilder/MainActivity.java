package com.example.a2_quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    // declaring UI objects and TAG
    Button btn_startQuiz;
    EditText ed_username;
    TextView tv_enterName;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_startQuiz = findViewById(R.id.btn_startQuiz);
        ed_username = findViewById(R.id.ed_username);
        tv_enterName = findViewById(R.id.tv_enterName);

        btn_startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed_username.getText().toString().equals("")) {
                    try {
                        Intent i = new Intent("ActivityQuiz");
                        Bundle extras = new Bundle();
                        extras.putString("username", ed_username.getText().toString());
                        i.putExtras(extras);
                        startActivityForResult(i, 1);
                    }catch(Exception e){
                        Log.e(TAG, e.getMessage());
                    }
                }
                else{
                    tv_enterName.setTextColor(Color.RED);
                    tv_enterName.setTypeface(tv_enterName.getTypeface(), Typeface.BOLD);
                }
            }
        }); // end startQuiz handler
    } // end onCreate
} // end MainActivity