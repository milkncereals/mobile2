package earl.com.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Highscore extends AppCompatActivity {

    private Button clearHighscoreBtn;
    private Button backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);

        clearHighscoreBtn = (Button)findViewById(R.id.clearHighscoreButton);
        backBtn = (Button)findViewById(R.id.back);

        displayHighscore();

        clearHighscoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(Highscore.this);
                db.clearHighscore();
                displayHighscore();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Highscore.this,WelcomeActivity.class));
            }
        });
    }

    private void displayHighscore(){

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor c = db.getAllHighscores();
        //maps the data from the sql to the layout views
        String[] from = new String[]{DatabaseHelper.NAME_COL, DatabaseHelper.COUNTRY_COL, DatabaseHelper.AGE_COL,DatabaseHelper.GENDER_COL,DatabaseHelper.RESULT_COL};
        int[] to = new int[]{R.id.tV_name, R.id.tV_country, R.id.tV_age,R.id.tV_gender,R.id.tV_result};

        //make an adapter to insert each item list to the scrollview and display them
        SimpleCursorAdapter highscoreAdapter;
        highscoreAdapter = new SimpleCursorAdapter(this, R.layout.highscore_item_list, c, from, to, 0);
        ListView highscoreList = (ListView) findViewById(R.id.lV_highscore);
        highscoreList.setAdapter(highscoreAdapter);
        highscoreList.setTextFilterEnabled(true);

    }

    public void onBackPressed() {
        //do nothing
    }
}