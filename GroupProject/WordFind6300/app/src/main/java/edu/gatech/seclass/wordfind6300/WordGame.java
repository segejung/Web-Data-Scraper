package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.WindowManager;

public class WordGame extends AppCompatActivity {
    GridView board;

    int numberOfMinutes, boardSize, lettersCount;

    public int finalScore = 0;

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);

        // Keeps the display of components from shifting when the soft keyboard displays when typing
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        // Gets the data of the settings from the Main Screen
        Intent intent = getIntent();

        numberOfMinutes = intent.getIntExtra("minutes", 3);
        boardSize = intent.getIntExtra("boardSize", 4);

        lettersCount = boardSize * boardSize;

        // randomly generates the board
        generateRandom();

        board = findViewById(R.id.boardGrid);

        // Set the number of columns of the board
        board.setNumColumns(boardSize);

        // Set the board with the data from the randomized list of letters
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        board.setAdapter(adapter);

        board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Randomly generates the board
    public void generateRandom()
    {
        list = new ArrayList<>();

        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
        String vowels = "AEIOU";

        int consonantCount = 0;
        int vowelCount = 0;

        // Set the consonantCount to 80% of the board size and vowelCount to 20% of the board size
        // rounded up
        consonantCount = (int) Math.floor((double) lettersCount * .80);
        vowelCount = (int) Math.ceil((double) lettersCount * .20);

        Random r = new Random();

        // Loop through the consonantCount and add a random consonant
        for (int i = 0; i < consonantCount; i++)
        {
            list.add(Character.toString(consonants.charAt(r.nextInt(consonants.length())))); // Add consonant
        }

        // Loop through the vowelCount and add a random vowel
        for (int i = 0; i < vowelCount; i++) {
            list.add(Character.toString(vowels.charAt(r.nextInt(vowels.length())))); // Add vowel
        }

        // Shuffle the list to randomize the board
        Collections.shuffle(list);

    }
}
