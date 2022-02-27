package com.example.a2_quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class ActivityResults extends AppCompatActivity {

    // declaring UI objects, and variables to calculate user's results
    TextView tv_resultsSubtitle, tv_correctCount, tv_incorrectCount, tv_overallPercentage;
    Button btn_playAgain;
    String username;
    int correctCount, incorrectCount;
    private static final String TAG = "ActivityResults";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // getting bundle from previous activity
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            correctCount = extras.getInt("correctAnswers");
            incorrectCount = extras.getInt("incorrectAnswers");
        }

        tv_resultsSubtitle = findViewById(R.id.tv_resultsSubtitle);
        tv_correctCount = findViewById(R.id.tv_correctCount);
        tv_incorrectCount = findViewById(R.id.tv_incorrectCount);
        tv_overallPercentage = findViewById(R.id.tv_overallPercentage);
        btn_playAgain = findViewById(R.id.btn_playAgain);

        // setting text for results, includes username via bundle
        String message = username + ", here are your results";
        tv_resultsSubtitle.setText(message);

        // setting text for results. determining percent, setting text for that as well
        tv_correctCount.setText(String.valueOf(correctCount));
        tv_incorrectCount.setText(String.valueOf(incorrectCount));
        double correctCountDouble = correctCount;
        double incorrectCountDouble = incorrectCount;
        double percentage = 100 * correctCountDouble / (correctCountDouble + incorrectCountDouble);
        @SuppressLint("DefaultLocale") String percentageMsg = (String.format("%.0f", percentage) + "%");
        tv_overallPercentage.setText(percentageMsg);

        // play again handler - if user clicks, return to first screen
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent resultsIntent = new Intent(ActivityResults.this, MainActivity.class);
                    startActivity(resultsIntent);
                }catch(Exception e){
                    Log.e(TAG, e.getMessage());
                }
            }
        }); // end playAgain handler
    } // end onCreate
}