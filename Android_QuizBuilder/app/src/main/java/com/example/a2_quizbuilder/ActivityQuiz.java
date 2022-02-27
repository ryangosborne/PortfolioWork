package com.example.a2_quizbuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.*;

import java.util.ArrayList;


public class ActivityQuiz extends AppCompatActivity {

    // declaring UI objects, button array list and TAG
    TextView tv_questionCounter, tv_term, tv_username;
    Button btn_choice1, btn_choice2, btn_choice3, btn_choice4, btn_next;
    Quiz quiz = new Quiz(this); // new Quiz object, sending context
    ArrayList<String> buttonArray = new ArrayList<String>();
    private static final String TAG = "ActivityQuiz";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tv_questionCounter = findViewById(R.id.tv_questionCounter);
        tv_term = findViewById(R.id.tv_term);
        tv_username = findViewById(R.id.tv_username);
        btn_choice1 = findViewById(R.id.btn_choice1);
        btn_choice2 = findViewById(R.id.btn_choice2);
        btn_choice3 = findViewById(R.id.btn_choice3);
        btn_choice4 = findViewById(R.id.btn_choice4);
        btn_next = findViewById(R.id.btn_next);

        // getting username, setting Quiz's username as such and showing username on quiz page
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String username = extras.getString("username");
            quiz.setUsername(username);
            tv_username.setText(quiz.getUsername());
        }

        // get quiz object to read file, add stuff to lists, hash map
        try {
            quiz.createQuizContent();
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }

        // update question counter
        tv_questionCounter.setText(quiz.incrementQuestionCounter());

        // get first term
        tv_term.setText(quiz.getNextTerm());

        // get 4 definitions, populate buttons for first time
        updateButtons();

        // making next button invisible
        btn_next.setVisibility(View.INVISIBLE);

        // definition buttons use same listener class
        btn_choice1.setOnClickListener(buttonSelected);
        btn_choice2.setOnClickListener(buttonSelected);
        btn_choice3.setOnClickListener(buttonSelected);
        btn_choice4.setOnClickListener(buttonSelected);

        // handler for next button
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

    } // end onCreate

    // handler for the four definition buttons
    public View.OnClickListener buttonSelected = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_choice1:
                    evaluateAnswer(btn_choice1);
                    break;
                case R.id.btn_choice2:
                    evaluateAnswer(btn_choice2);
                    break;
                case R.id.btn_choice3:
                    evaluateAnswer(btn_choice3);
                    break;
                case R.id.btn_choice4:
                    evaluateAnswer(btn_choice4);
                    break;
            } // end switch
        } // end onClick
    }; // end inner class buttonSelected


    // Activity Methods
    public void evaluateAnswer(Button selectedButton){
        /*
         setting definition buttons to not clickable. if correct answer was selected, call logAnswer
         method in Quiz to log answer as correct, and turn button text to green. if answer was
         incorrect, call method in Quiz to log as incorrect, then change colour of button to red.
         find the correct answer and turn that green. then make the NEXT button visible
        */
        buttonsClickableSetting(false);

        if(selectedButton.getText().toString().equals(quiz.getCurrentDefinition())){
            quiz.logAnswer(true);
            selectedButton.setTextColor(Color.GREEN);
        }
        else{
            quiz.logAnswer(false);
            selectedButton.setTextColor(Color.RED);
            if(btn_choice1.getText().toString().equals(quiz.getCurrentDefinition())){
                btn_choice1.setTextColor(Color.GREEN);
            }
            else if(btn_choice2.getText().toString().equals(quiz.getCurrentDefinition())){
                btn_choice2.setTextColor(Color.GREEN);
            }
            else if(btn_choice3.getText().toString().equals(quiz.getCurrentDefinition())){
                btn_choice3.setTextColor(Color.GREEN);
            }
            else if(btn_choice4.getText().toString().equals(quiz.getCurrentDefinition())){
                btn_choice4.setTextColor(Color.GREEN);
            }
        }
        btn_next.setVisibility(View.VISIBLE);
    } // end checkAnswer

    public void nextQuestion(){
        /*
        this method will check if the quiz has been completed by calling a method from the quiz object
        if it is, put variables needed for results in bundle, and send with intent to results page.
        otherwise, call the resetButtons method, get a new term from the quiz and update the UI to show it.
        call the updateButtons method, then call a quiz function to return an updated string for the
        question counter text on UI
         */
        if(quiz.checkQuizCompletion()){
            try {
                Intent intent = new Intent("ActivityResults");
                Bundle bundle = new Bundle();
                bundle.putString("username", quiz.getUsername());
                bundle.putInt("correctAnswers", quiz.getCorrectAnswers());
                bundle.putInt("incorrectAnswers", quiz.getIncorrectAnswers());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }catch(Exception e){
                Log.e(TAG, e.getMessage());
            }
        }
        else {
            resetButtons();
            tv_term.setText(quiz.getNextTerm());
            updateButtons();
            tv_questionCounter.setText(quiz.incrementQuestionCounter());
        }
    } // end nextQuestion

    public void updateButtons(){
        /*
         this is called after user hits next question button. this will get 4 new definitions from
         Quiz object stored in array. this method assigns the strings from that array into the four
         definition buttons
        */
        buttonArray = quiz.getButtons();
        btn_choice1.setText(buttonArray.get(0));
        btn_choice2.setText(buttonArray.get(1));
        btn_choice3.setText(buttonArray.get(2));
        btn_choice4.setText(buttonArray.get(3));
    } // end updateButtons

    public void resetButtons(){
        /*
         this method will reset any and every definition button's font back to white and let them
         be clickable, then turns the Next button to invisible.
        */
        btn_choice1.setTextColor(Color.WHITE);
        btn_choice2.setTextColor(Color.WHITE);
        btn_choice3.setTextColor(Color.WHITE);
        btn_choice4.setTextColor(Color.WHITE);
        buttonsClickableSetting(true);
        btn_next.setVisibility(View.INVISIBLE);
    } // end resetButtonAppearance

    public void buttonsClickableSetting(boolean setting){
        /*
         this method will turn the definition buttons to be clickable or not clickable, depending on
         the boolean parameter passed into the method
        */
        btn_choice1.setClickable(setting);
        btn_choice2.setClickable(setting);
        btn_choice3.setClickable(setting);
        btn_choice4.setClickable(setting);
    } // end buttonsClickableSetting
}