package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuizActivity extends AppCompatActivity {
    private  int numOfClicks;
    private  int userNumber;
    private  String outputString;
    private  int numInput1;
    private  int numInput2;
    private  int numofIncorrectness;
    private  int numofConsecutiveCorrectness;
    private  String difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        difficulty = getIntent().getStringExtra("Diff");
        initialize();

    }

    private void initialize() {
        userNumber = 0;
        numOfClicks = 0;
        outputString = "";
        numofIncorrectness = 0;
        TextView userInput = findViewById(R.id.userInput);
        userInput.setText("    ?");
        userInput.setTextColor(Color.BLUE);
        TextView textInput1 = findViewById(R.id.textInput1);
        TextView textInput2 = findViewById(R.id.textInput2);
        Random random = new Random();
        if (difficulty.equals("Easy")){
            numInput1 = random.nextInt(90) + 10;
            int firstremainder = numInput1 % 10;
            int firstdigitbound = 10 - firstremainder;
            int firstdigit = random.nextInt(firstdigitbound);
            Log.d("start", String.valueOf(firstdigit));
            int tenthremainder = numInput1 / 10;
            int tenthdigitbound = 10 - tenthremainder;
            int tenthdigit = random.nextInt(tenthdigitbound-1) + 1;
            Log.d("start", String.valueOf(tenthdigit));
            numInput2 = tenthdigit * 10 + firstdigit;
        }
        else if (difficulty.equals("Medium")){
            numInput1 = random.nextInt(90) + 10;
            int firstremainder = numInput1 % 10;
            int firstdigit = random.nextInt(10);
            int tenthdigit = 0;
            if (firstdigit + firstremainder < 10) {
                int tenthremainder = numInput1 / 10;
                int tenthdigitbound = 10 - tenthremainder;
                tenthdigit = random.nextInt(10 - tenthdigitbound) + tenthdigitbound;
            }
            else {
                int tenthremainder = numInput1 / 10;
                int tenthdigitbound = 10 - tenthremainder;
                tenthdigit = random.nextInt(tenthdigitbound);
            }
            numInput2 = tenthdigit * 10 + firstdigit;
        }
        else {
            numInput1 = random.nextInt(90) + 10;
            int firstremainder = numInput1 % 10;
            int firstdigitbound = 10 - firstremainder;
            int firstdigit = random.nextInt(10 - firstdigitbound) + firstdigitbound;
            int tenthremainder = numInput1 / 10;
            int tenthdigitbound = 9 - tenthremainder;
            int tenthdigit = random.nextInt(9 - tenthdigitbound) + tenthdigitbound;
            numInput2 = tenthdigit * 10 + firstdigit;
        }
        textInput1.setText(String.valueOf(numInput1));
        textInput2.setText(String.valueOf(numInput2));
    }
    public void onNumberButtonClick(View v) {
        int num = 0;
        int id = v.getId();
        if (id == R.id.button1){
            num = 1;
        }
        else if (id == R.id.button2){
            num = 2;
        }
        else if (id == R.id.button3){
            num = 3;
        }
        else if (id == R.id.button4){
            num = 4;
        }
        else if (id == R.id.button5){
            num = 5;
        }
        else if (id == R.id.button6){
            num = 6;
        }
        else if (id == R.id.button7){
            num = 7;
        }
        else if (id == R.id.button8){
            num = 8;
        }
        else if (id == R.id.button9){
            num = 9;
        }
        numOfClicks++;
        compileTextforNumber(num);
    }

    private void compileTextforNumber(int num) {
        TextView userInput = findViewById(R.id.userInput);
        String blank = "";
        if (numOfClicks == 1){
            blank = "   ";
            outputString =  String.valueOf(num);
            userNumber = num;
        }
        else if (numOfClicks == 2) {
            blank = "  ";
            outputString = num + outputString;
            userNumber = num * 10 + userNumber;

        }
        else if (numOfClicks == 3) {
            blank = " ";
            outputString = num + outputString;
            userNumber = num * 100 + userNumber;

        }
        userInput.setText(blank + outputString);
    }

    public void onClearButtonClick(View v) {
        userNumber = 0;
        numOfClicks = 0;
        outputString = "";
        TextView userInput = findViewById(R.id.userInput);
        userInput.setText("    ?");
    }

    public void onSubmitButtonClick(View v) {
        int answer = numInput1 + numInput2;
        if (numOfClicks == 0){
            Toast.makeText(QuizActivity.this, "Please enter an answer! ", Toast.LENGTH_SHORT).show();
        }
        else if (userNumber == answer){
            numofConsecutiveCorrectness++;
            if (numofConsecutiveCorrectness > 1){
                Toast.makeText(QuizActivity.this, "Correct! " + numofConsecutiveCorrectness + " in a row!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(QuizActivity.this, "Correct! ", Toast.LENGTH_SHORT).show();
            }
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute( () -> {
                try { Thread.sleep(3000); } catch (Exception e) { }
                initialize();
            });
        }
        else {
            numofIncorrectness++;
            boolean restart= false;
            if (numofIncorrectness < 3){
                Toast.makeText(QuizActivity.this, "Incorrect! Try again!", Toast.LENGTH_SHORT).show();
                userNumber = 0;
                numOfClicks = 0;
                outputString = "";
                TextView userInput = findViewById(R.id.userInput);
                userInput.setText("   ?");
            }
            else{
                Toast.makeText(QuizActivity.this, "Incorrect! The correct answer is " + answer, Toast.LENGTH_SHORT).show();
                TextView userInput = findViewById(R.id.userInput);
                userInput.setTextColor(Color.RED);
                userInput.setText("  " + answer);
                restart = true;
                numofConsecutiveCorrectness = 0;
            }
            Executor executor = Executors.newSingleThreadExecutor();
            if (restart == true){
                executor.execute( () -> {
                    try { Thread.sleep(3000); } catch (Exception e) { }
                    initialize();
                });
            }
            else {
                executor.execute( () -> {
                    try { Thread.sleep(3000); } catch (Exception e) { }
                });
            }


        }

    }
}