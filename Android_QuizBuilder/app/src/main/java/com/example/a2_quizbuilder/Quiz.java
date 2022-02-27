package com.example.a2_quizbuilder;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Quiz {

    // properties
    private int currentQuestion;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private String currentTerm;
    private String currentDefinition;
    private String username;
    private ArrayList<String> terms = new ArrayList<String>();
    private ArrayList<String> definitions = new ArrayList<String>();
    private Map<String,String> map = new HashMap<String,String>();
    private ArrayList<String> buttons = new ArrayList<String>();
    private Context context;

    // (default) constructor
    Quiz(Context context){
        this.currentQuestion = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.context = context;
    }

    // methods
    public void createQuizContent(){
        /*
         reading file from assets folder. reading line by line and splitting the string at delimiter ($),
         storing in corresponding array lists and hash map. run through until end of file, close file.
         determine and set quiz's question count (length of terms list), and shuffling terms list.
         if the file can not be opened, the exception will be logged
        */
        try {
            InputStream input = context.getAssets().open("quizBuilderText.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String str;
            while ((str = br.readLine()) != null) {
                String[] extracts = str.split("\\$", 2);
                terms.add(extracts[0]);
                definitions.add(extracts[1]);
                map.put(extracts[0], extracts[1]);
            }
            br.close();
        }catch(IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        totalQuestions = terms.size();
        Collections.shuffle(terms);
    } // end createQuizContent


    public String getNextTerm(){
        /*
         finds next term in list, updates object properties to reflect new term, gets associated
         definition value from hash map and stores updates object properties. burns term from list,
         returns new term as string
        */
        currentTerm = terms.get(0);
        currentDefinition = map.get(currentTerm);
        terms.remove(0);
        return currentTerm;
    } // end getNextTerm


    public String incrementQuestionCounter(){
        /*
        current question is incremented. updated string is returned
        */
        currentQuestion ++;
        return "Question " + currentQuestion + " of " + totalQuestions;
    } // end incrementQuestionCounter


    public void logAnswer(boolean answer){
        /*
        method will increment either a correct or incorrect answer depending on which boolean value
        is passed
         */
        if (answer){
            correctAnswers++;
        } else{
            incorrectAnswers++;
        }
    } // end logAnswer


    public ArrayList<String> getButtons(){
        /*
         get correct definition and three other definitions, store in button list.
         other three definitions will be randomly picked from list. before adding to buttons list,
         check if list contains that element. if not, add it, if it does, don't add.
         loop will run until buttons list has 4 elements, shuffle list, then return list to activity.
        */
        buttons.clear();
        buttons.add(currentDefinition);

        Random random = new Random();
        do {
            String randDef = definitions.get(random.nextInt(definitions.size()));
            if(!buttons.contains(randDef)){
                buttons.add(randDef);
            }
        } while(buttons.size() < 4);
        Collections.shuffle(buttons);
        return buttons;
    } // end getButtons


    public boolean checkQuizCompletion(){
        /*
        this method is called at the end of each answered question before proceeded to a new question.
        this method will determine if the quiz is complete by checking if the current question (just answered)
        is of the same value as the total questions. if it is end of quiz, return true. if it's not,
        return false.
         */
        if (currentQuestion == totalQuestions){
            return true;
        }
        else{
            return false;
        }
    }




    // getters & setters
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public String getCurrentDefinition(){
        return currentDefinition;
    }
    public int getCorrectAnswers(){
        return correctAnswers;
    }
    public int getIncorrectAnswers(){
        return incorrectAnswers;
    }
}
