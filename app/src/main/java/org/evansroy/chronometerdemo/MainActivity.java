package org.evansroy.chronometerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //initialize variable
    Chronometer chronometer;
    Button btReset;
    SesseionManager sesseionManager;
    SimpleDateFormat format;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variables
        chronometer = findViewById(R.id.tv_chronometer);
        btReset = findViewById(R.id.bt_reset);

        //initialize session Manager
        sesseionManager = new SesseionManager(getApplicationContext());
        //Initialize simple date format
        format = new SimpleDateFormat("hh:mm:ss aa");
        //Get current time
        currentTime = format.format(new Date());

        //Get Flag from session manager
        boolean flag = sesseionManager.getFlag();
        //Check Condition
        if (!flag){
            //When flag is false
            //set Current time
            sesseionManager.setCurrentTime(currentTime);
            //Set flag
            sesseionManager.setFlag(true);
            //Start chronometer
            chronometer.start();
        }else {
            //When flag is true
            //Get session manager current time
            String sessionManagerCurrentTime = sesseionManager.getCurrentTime();

            try {
                // Convert string to date
                Date date1 = format.parse(sessionManagerCurrentTime);
                Date date2 = format.parse(currentTime);
                //Calculate time difference
                long mils = date2.getTime() - date1.getTime();
                //Set base
                chronometer.setBase(SystemClock.elapsedRealtime() - mils);
                //Start chronometer
                chronometer.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset chronometer
                chronometer.setBase(SystemClock.elapsedRealtime());
                //Set current time
                sesseionManager.setCurrentTime(currentTime);
                //Start chronometer
                chronometer.start();
            }
        });


    }
}
