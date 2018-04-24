package earl.com.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class SaveDetails extends AppCompatActivity{


    private EditText name;
    private EditText age;
    private Spinner countrySpinner;
    private String player_gender;
    private RadioGroup genderGroup;
    private RadioButton genderButton;


    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_activity);
        name = (EditText)findViewById(R.id.nameEt);
        age = (EditText)findViewById(R.id.ageEt);


        //populate the country list
        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<CharSequence> country_adapter = ArrayAdapter.createFromResource(this, R.array.countries_array, android.R.layout.simple_spinner_item);
        country_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(country_adapter);

        //Buttons
        Button saveBtn = (Button)findViewById(R.id.beginButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String player_name = name.getText().toString();
                final String player_age = age.getText().toString();

                //get the selected country
                final Spinner player_country_spinner =(Spinner) findViewById(R.id.countrySpinner);
                final String player_country = player_country_spinner.getSelectedItem().toString();

                //get the gender
                genderGroup =(RadioGroup)findViewById(R.id.genderRadioGroup);
                int id = genderGroup.getCheckedRadioButtonId();
                genderButton = (RadioButton)findViewById(id);
                String player_gender = genderButton.getText().toString();

                //Getting the passed result from the quiz
                Intent intentExtras = getIntent();
                Bundle extrasBundle = intentExtras.getExtras();
                String player_result = extrasBundle.getString("result");

                //check for error empty fields
                if (player_name.equals("") || player_age.equals("")) {
                    Toast.makeText(SaveDetails.this, "Empty Fields!", Toast.LENGTH_LONG).show();
                }else{
                    //Add result to the database
                    DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
                    myDb.addNewResult(player_name,player_country,player_age,player_gender,player_result);
                    startActivity(new Intent(SaveDetails.this,Highscore.class));

                }
            }
        });


    }

    public void onRadioButtonClicked(View view) {

        //check if a button is checked
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    player_gender = "Male";
                    break;
            case R.id.radio_female:
                if (checked)

                    player_gender = "Female";
                    //female
                    break;
        }
    }

 }


