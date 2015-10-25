package com.example.dexter.game;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ArithmeticTasks extends AppCompatActivity implements View.OnClickListener {

    TextView textView2;
    TextView textView3;
    EditText editText1;
    Button buttonResult1;
    int i = 0;
    int task;
    int n;

    int numb_of_points;

    int [] tasksEasyResults = {11,20,5,9,7,1,35,56,0,9};
    String [] taskEasy = {"5+6", "4*5", "9-4", "3*3", "20-13", "17/17", "7*5", "7*8", "0/5","81/9"};


    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SECONDS_TO_COUNTDOWN = 10;
    private TextView countdownDisplay;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic_tasks);

        countdownDisplay = (TextView) findViewById(R.id.time_display_box);
        //Button startButton = (Button) findViewById(R.id.button1);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        editText1 = (EditText) findViewById(R.id.editText1);
        buttonResult1 = (Button) findViewById(R.id.buttonResult1);

        textView2.setText(taskEasy[0]);
        buttonResult1.setOnClickListener(this);

        try {
            showTimer(SECONDS_TO_COUNTDOWN * MILLIS_PER_SECOND);
        } catch (NumberFormatException e) {
            // method ignores invalid (non-integer) input and waits
            // for something it can use
        }
    }



    private void showTimer(int countdownMillis) {
        if(timer != null) { timer.cancel(); }
        timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownDisplay.setText("counting down: " +
                        millisUntilFinished / MILLIS_PER_SECOND);
            }
            @Override
            public void onFinish() {

            countdownDisplay.setText("Time out!");
            i++;

            }
            }.start();
    }


    public void onClick (View v) {
        if (i == n+2) {
            textView2.setText(taskEasy[i]);
            timer.start();
        }

        if (TextUtils.isEmpty(editText1.getText().toString())) {
            return;
        }

        if (i < tasksEasyResults.length) {
            timer.cancel();
            timer.start();
            task = Integer.parseInt(editText1.getText().toString());
            n = i;
            if (task == tasksEasyResults[i]) {
                textView3.setText("true");
                numb_of_points += 1;
            } else {
                textView3.setText("false");
            }

            i++;

            if (i == tasksEasyResults.length) {
                textView3.setText("Scores: " + String.valueOf(numb_of_points)+ "/10");
                timer.cancel();
            }
            if (i<tasksEasyResults.length){textView2.setText(taskEasy[i]);}


        }
        editText1.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arithmetic_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
