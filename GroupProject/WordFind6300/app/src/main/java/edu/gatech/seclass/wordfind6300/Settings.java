package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Settings extends AppCompatActivity {
    Spinner gameMinutesSpinner, boardSizeSpinner;

    // presets for minutes and board sizes
    String[] minutes = new String[] {"1", "2", "3", "4", "5"};
    String[] sizes = new String[] {"4(x4)", "5(x5)", "6(x6)", "7(x7)", "8(x8)"};
    final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    TextView letterSelectorValueText;
    TextView weightSelectorValueText;

    SeekBar letterSeekbar;
    SeekBar weightSeekbar;

    int letterSelectorValue = 0;
    int weightSelectorValue = 1;

    int numberOfMinutes = 3;
    int boardSize = 4;
    StatObject so = null;
    char letterToChange = 'A';


    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        so = ((StatObject)this.getApplication());

        gameMinutesSpinner = findViewById(R.id.gameMinutesSpinner);
        boardSizeSpinner = findViewById(R.id.boardSizeSpinner);

        // initialize gameMinutesSpinner with the minutes presets and default to 3
        ArrayAdapter<String> minutesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, minutes);
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameMinutesSpinner.setAdapter(minutesAdapter);
        gameMinutesSpinner.setSelection(2);

        // initialize boardSizeSpinner with sizes presets and default to 4(x4)
        ArrayAdapter<String> sizesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sizes);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpinner.setAdapter(sizesAdapter);
        boardSizeSpinner.setSelection(0);

        // When gameMinutesSpinner is clicked, set numberOfMinutes to the item selected
        gameMinutesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                numberOfMinutes = Integer.parseInt(parentView.getItemAtPosition(position).toString());
                so.minutes = numberOfMinutes;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                return;
            }

        });

        // When boardSizeSpinner is clicked, set boardSize to the item selected
        boardSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String boardSizeString = parentView.getItemAtPosition(position).toString();
                boardSize = Character.getNumericValue(boardSizeString.charAt(0));
                so.size = Integer.valueOf(boardSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                return;
            }

        });

        letterSelectorValueText = findViewById(R.id.letterSelectorValueText);
        weightSelectorValueText = findViewById(R.id.weightSelectorValueText);
        letterSeekbar = findViewById(R.id.letterSeekbar);
        weightSeekbar = findViewById(R.id.weightSeekbar);
        letterSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                letterToChange = LETTERS.charAt(progressChangedValue);
                letterSelectorValueText.setText(String.valueOf(letterToChange));
            }
        });

        weightSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                weightSelectorValueText.setText(String.valueOf(progressChangedValue));
                so.letterWeight.put(letterToChange, progressChangedValue);
            }
        });

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });
    }

    // When back button is pressed at the bottom left of screen, return data from settings back to
    // Main UI
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("minutes", numberOfMinutes);
        intent.putExtra("boardSize", boardSize);
        setResult(RESULT_OK, intent);
        finish();
    }

    // When the back button at the top left of the screen is clicked, return data from settings back
    // to Main UI
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do something useful
                Intent intent = new Intent();
                intent.putExtra("minutes", numberOfMinutes);
                intent.putExtra("boardSize", boardSize);
                setResult(RESULT_OK, intent);
                finish();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    public void writeFile(){
        StatObject stat = this.readFile();
        try{
            //Saving of object in a file
            FileOutputStream file = openFileOutput("data.ser", MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(file);

            stat.saveAllToList();
            // Method for serialization of object
            out.writeObject(stat);
            out.close();
            file.close();

            Toast.makeText(getApplicationContext(), "Object has been serialized", Toast.LENGTH_SHORT).show();
        } catch(IOException ex){
            Toast.makeText(getApplicationContext(), "IOException is caught", Toast.LENGTH_SHORT).show();
        }
    }
    public StatObject readFile(){
        StatObject stat = null;
        try{
            // Reading the object from a file
            FileInputStream file = openFileInput("data.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            stat = (StatObject) in.readObject();

            in.close();
            file.close();
//            displayText.setText("the last value stored in the allWordsMap is \n" + stat.allWordsMap.get(stat.allWordsMap.size() - 1));
        } catch(IOException ex){
            Toast.makeText(getApplicationContext(), "IOException is caught, initializing a new StatObject", Toast.LENGTH_SHORT).show();
            stat =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return stat;
    }
}
