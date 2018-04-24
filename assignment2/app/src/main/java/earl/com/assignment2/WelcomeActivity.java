package earl.com.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


//https://developer.android.com/training/gestures/detector.html


public class WelcomeActivity extends Activity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static final int SPLASH_TIME = 5000;
    private TextView welcomeMessage;
    private static final String DEBUG_TAG = "Gestures";
    private ProgressBar welcomeProgressBar;
    private Button HighscoreBtn;
    private Button exitBtn;
    private GestureDetectorCompat mDetector;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen_activity);
        welcomeMessage = (TextView)findViewById(R.id.welcomeTv);
        welcomeProgressBar = (ProgressBar)findViewById(R.id.welcomeProgressBar);

        HighscoreBtn = (Button)findViewById(R.id.highscoreBtn);
        exitBtn = (Button)findViewById(R.id.exitBtn);

        HighscoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to highscore activity
                startActivity(new Intent(WelcomeActivity.this,Highscore.class));
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit the app
                finish();
                System.exit(0);
            }
        });



        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {

        //swipes to the right
        if(event1.getX() - event2.getX() < 50){

        }

        //swipes to the left.
        if(event2.getX() - event1.getX() < 50){

            new CountDownTimer(5000, 1000) {

                int progressNumber = 0;

                public void onTick(long millisUntilFinished) {
                    welcomeMessage.setText("Starting in " + millisUntilFinished / 1000);
                    progressNumber += 10;
                    welcomeProgressBar.setProgress(progressNumber);

                }

                public void onFinish() {
                    startActivity(new Intent(WelcomeActivity.this,Quiz.class));
                    finish();
                }
            }.start();


        }
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}