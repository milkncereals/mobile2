package earl.com.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    private TextView question;
    private TextView questionNumber;
    private Button trueBtn;
    private Button falseBtn;
    private ProgressBar progressBar;
    private Button endQuizBtn;
    private Button saveScoreBtn;
    private Questions questions = new Questions();
    private int questionNo = 0;
    private int results = 0;
    private int currentProgress = 0;
    private int progressBarInc = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quiz_layout);

        questionNumber = (TextView)findViewById(R.id.questionNumberTv);
        question = (TextView) findViewById(R.id.questionTv);
        trueBtn = (Button) findViewById(R.id.trueButton);
        falseBtn = (Button) findViewById(R.id.falseButton);
        endQuizBtn = (Button)findViewById(R.id.endTestButton);
        saveScoreBtn = (Button)findViewById(R.id.saveHighScoreButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //starts the quiz
        startQuiz();

        saveScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //Create an intent bundle to send data to the result class
                    Intent intentBundle = new Intent(Quiz.this,SaveDetails.class);
                    //Create a bundle.
                    Bundle bundle = new Bundle();
                    //Put the result value inside the bundle
                    bundle.putFloat("result",results);
                    //Put the bundle in the intent bundle.
                    intentBundle.putExtras(bundle);
                    //Start the activity.
                    startActivity(intentBundle);


            }
        });

        endQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(Quiz.this,WelcomeActivity.class));


            }
        });

    }

    public void startQuiz(){
        //start the question
        nextQuestion(questionNo);

        //users clicks true
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if answer is correct increment the results
                if(questions.getAnswer(questionNo).equals("True")){
                    results++;
                }else{
                    Toast.makeText(Quiz.this, "The answer is false", Toast.LENGTH_SHORT).show();
                }

                //update to the next question.
                nextQuestion(questionNo);

            }
        });

        //user clicks false
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if answer is correct increment the results
                if(questions.getAnswer(questionNo).equals("False")){
                    results++;
                }else{
                    Toast.makeText(Quiz.this, "The answer is true", Toast.LENGTH_SHORT).show();
                }

                //update to the next question
                nextQuestion(questionNo);
            }
        });

    }

    public void nextQuestion(int a){

        //quiz is finished
        if(questionNo == questions.getQuestionSize() - 1 ){

            //Change the text of the Question Number
            questionNumber.setText("End of Quiz");

            //set the progress, starting at 10%
            progressBar.setProgress(currentProgress + ( (questionNo * 10) + 10) );

            question.setText("You Scored " + results + " Out Of " + questions.getQuestionSize());

            falseBtn.setVisibility(View.GONE);
            trueBtn.setVisibility(View.GONE);


        }else{
            //change the text question
            question.setText(questions.getQuestion(a));

            //set the progress, starting at 10%
            progressBar.setProgress(currentProgress + ( (questionNo * progressBarInc) + progressBarInc) );

            //Change the text of the Question Number
            questionNumber.setText("Question " + (questionNo + 1));
            //Increment the question number
            questionNo++;

        }
    }

    public void onBackPressed() {
        //do nothing
    }
}
