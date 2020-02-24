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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Intent intent = getIntent();

        numberOfMinutes = intent.getIntExtra("minutes", 3);
        boardSize = intent.getIntExtra("boardSize", 4);

        lettersCount = boardSize * boardSize;

        generateRandom();

        board = findViewById(R.id.boardGrid);
        board.setNumColumns(boardSize);
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

    public void generateRandom()
    {
        list = new ArrayList<>();

        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
        String vowels = "AEIOU";

        int consonantCount = 0;
        int vowelCount = 0;

        consonantCount = (int) Math.floor((double) lettersCount * .80);
        vowelCount = (int) Math.ceil((double) lettersCount * .20);

        Random r = new Random();

        for (int i = 0; i < consonantCount; i++)
        {
            list.add(Character.toString(consonants.charAt(r.nextInt(consonants.length())))); // Add consonant
        }

        for (int i = 0; i < vowelCount; i++) {
            list.add(Character.toString(vowels.charAt(r.nextInt(vowels.length())))); // Add vowel
        }
        Collections.shuffle(list);

    }
}
