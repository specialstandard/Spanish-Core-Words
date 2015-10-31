package com.standardgeneral.android.spanishcorewords;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonNext;
    private Button button;
    public String Answer;
    public String[] buttonValues;
    HashMap<String, String> map = new HashMap<String, String>();
    private static final int[] BUTTON_IDS = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button6
    };
    private Integer mQuestionsAttempted;
    private Integer mQuestionsCorrect;
    private boolean mIsAnswered;

    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button buttonTimer = (Button)findViewById(R.id.buttonTimer);
        buttonTimer.setText("start");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        mQuestionsAttempted = new Integer(0);
        mQuestionsCorrect = new Integer(0);
        mIsAnswered = true;

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        Button buttonTimer = (Button) findViewById(R.id.buttonTimer);
        buttonTimer.setText("start");
        buttonTimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });

        addListenerOnButton();
    } //onCreate() method

    public void addListenerOnButton() {

        //Select a specific button to bundle it with the action you want
        buttonNext = (Button) findViewById(R.id.button6);


        buttonNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView t = (TextView) findViewById(R.id.textView1);
                mIsAnswered = false;

                for (int id : BUTTON_IDS){
                    Button btmp = (Button) findViewById(id);
                    btmp.setBackgroundResource(android.R.drawable.btn_default);
                }

                Words.addWords(map);
                Random generator = new Random();
                String[] keySet = map.keySet().toArray(new String[0]);
                String answerKey = keySet[generator.nextInt(keySet.length)];
                t.setText(answerKey); //Random Question
                Random generator2 = new Random();
                int num= generator2.nextInt(4); //Answer
                buttonValues = new String[4];
                String[] possibleValues = map.values().toArray(new String[0]);
                Answer = map.get(answerKey);
                for(int i=0; i<=3; i++){
                    if(i==0){
                        if(num==0){
                            buttonValues[0] = Answer; //This is the Answer
                        }
                        else {
                            Random generator3 = new Random();
                            int randomIndex;
                            String randomValue;
                            do{
                                randomIndex = generator3.nextInt(possibleValues.length);
                                randomValue = possibleValues[randomIndex];
                            }
                            while (Arrays.asList(buttonValues).contains(randomValue)
                                    || Answer == randomValue);
                            buttonValues[0] =randomValue;
                        }
                    }if(i==1){
                        if(num==1){
                            buttonValues[1] = Answer; //This is the Answer
                        }
                        else {
                            Random generator3 = new Random();
                            int randomIndex;
                            String randomValue;
                            do{
                                randomIndex = generator3.nextInt(possibleValues.length);
                                randomValue = possibleValues[randomIndex];
                            }
                            while (Arrays.asList(buttonValues).contains(randomValue)
                                    || Answer == randomValue);

                            buttonValues[1] =randomValue;
                        }
                    }
                    if(i==2){
                        if(num==2){
                            buttonValues[2] = Answer; //This is the Answer
                        }
                        else {
                            Random generator3 = new Random();
                            int randomIndex;
                            String randomValue;
                            do{
                                randomIndex = generator3.nextInt(possibleValues.length);
                                randomValue = possibleValues[randomIndex];
                            }
                            while (Arrays.asList(buttonValues).contains(randomValue)
                                    || Answer == randomValue);
                            buttonValues[2] =randomValue;
                        }
                    }
                    if(i==3){
                        if(num==3){
                            buttonValues[3] = Answer; //This is the Answer
                        }
                        else {
                            Random generator3 = new Random();
                            int randomIndex;
                            String randomValue;
                            do{
                                randomIndex = generator3.nextInt(possibleValues.length);
                                randomValue = possibleValues[randomIndex];
                            }
                            while (Arrays.asList(buttonValues).contains(randomValue)
                                    || Answer == randomValue);
                            buttonValues[3] =randomValue;
                        }
                    }
                }
                for (int i =0; i<4; i++){ // Assign Button Values
                    if(i==0){
                        button=(Button) findViewById(R.id.button1);
                        button.setText(buttonValues[i]);
                    }
                    if(i==1){
                        button=(Button) findViewById(R.id.button2);
                        button.setText(buttonValues[i]);
                    }
                    if(i==2){
                        button=(Button) findViewById(R.id.button3);
                        button.setText(buttonValues[i]);
                    }
                    if(i==3){
                        button=(Button) findViewById(R.id.button4);
                        button.setText(buttonValues[i]);
                    }
                } // for loop

            } //onClick() method

        }); // setOnClickListener() method

    } // addListenerOnButton() method

    public void clickFunc(View view){
        //TextView t2 = (TextView) findViewById(R.id.textView2);
        Button b = (Button)view;
        //t2.setText(b.getText().toString());
        //t2.setText(Answer);
        if (!mIsAnswered) {
            for (int i =0; i<4; i++){ // Iterate to find correct button and make it green
                if(i==0){
                    button=(Button) findViewById(R.id.button1);
                    if (button.getText().toString() == Answer) {
                        button.setBackgroundColor(0xFF00FF00);
                        if (button.getText().toString() == b.getText().toString()) {
                            mQuestionsCorrect += 1;
                        }
                    }
                }
                if(i==1){
                    button=(Button) findViewById(R.id.button2);
                    if (button.getText().toString() == Answer) {
                        button.setBackgroundColor(0xFF00FF00);
                        if (button.getText().toString() == b.getText().toString()) {
                            mQuestionsCorrect += 1;
                        }
                    }
                }
                if(i==2){
                    button=(Button) findViewById(R.id.button3);
                    if (button.getText().toString() == Answer) {
                        button.setBackgroundColor(0xFF00FF00);
                        if (button.getText().toString() == b.getText().toString()) {
                            mQuestionsCorrect += 1;
                        }
                    }
                }
                if(i==3){
                    button=(Button) findViewById(R.id.button4);
                    if (button.getText().toString() == Answer) {
                        button.setBackgroundColor(0xFF00FF00);
                        if (button.getText().toString() == b.getText().toString()) {
                            mQuestionsCorrect += 1;
                        }
                    }
                }
            } // for loop

            mQuestionsAttempted += 1;
            TextView t = (TextView)findViewById(R.id.textView_questions_correct);
            t.setText(mQuestionsCorrect.toString());
            TextView t2 = (TextView)findViewById(R.id.textView_questions_attempted);
            t2.setText(mQuestionsAttempted.toString());
            mIsAnswered = true;
        }//if(!mIsAnswered)
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // onCreateOptionsMenu() method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected() method


} //FlashActivity Class