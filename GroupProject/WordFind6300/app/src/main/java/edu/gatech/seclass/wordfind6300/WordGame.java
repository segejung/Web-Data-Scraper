package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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

    public void generateRandom(){
        list = new ArrayList<>();
        // A dummy map to simulate a weight map for now ------------------
        Map<Character, Integer> dummyMap = new HashMap();
        dummyMap.put('A', 1);
        dummyMap.put('B', 1);
        dummyMap.put('C', 1);
        dummyMap.put('D', 1);
        dummyMap.put('E', 1);
        dummyMap.put('D', 1);
        dummyMap.put('E', 1);
        dummyMap.put('F', 1);
        dummyMap.put('G', 1);
        dummyMap.put('H', 1);
        dummyMap.put('I', 1);
        dummyMap.put('J', 1);
        dummyMap.put('K', 1);
        dummyMap.put('L', 1);
        dummyMap.put('M', 1);
        dummyMap.put('N', 1);
        dummyMap.put('O', 1);
        dummyMap.put('P', 1);
        dummyMap.put('Q', 1);
        dummyMap.put('R', 1);
        dummyMap.put('S', 1);
        dummyMap.put('T', 1);
        dummyMap.put('U', 1);
        dummyMap.put('V', 1);
        dummyMap.put('W', 1);
        dummyMap.put('X', 1);
        dummyMap.put('Y', 1);
        dummyMap.put('Z', 1);

        String consonants = "";
        String vowels = "";

        for(Character c : dummyMap.keySet()){
            for (int i = 0; i < dummyMap.get(c); i++) {
                String C = Character.toString(c);
                if ("AEIOU".indexOf(C) >= 0) {
                    vowels += C;
                } else {
                    consonants += C;
                }
            }
        }

        // end of dummy map-------------------------------------------------

        int consonantCount = 0;
        int vowelCount = 0;

        // Take vowels string as example, it will ended up being "AAAEIOU" if A is weighted as 3.
        // Same as the consonants string.
        // When getting a random char from the string, the value is weighted.


        // Set the consonantCount to 80% of the board size and vowelCount to 20% of the board size
        // rounded up
        consonantCount = (int) Math.floor((double) lettersCount * .80);
        vowelCount = (int) Math.ceil((double) lettersCount * .20);

        Random r = new Random();

        for (int i = 0; i < consonantCount; i++){
            char c = consonants.charAt(r.nextInt(consonants.length()));
            if(c == 'Q'){
                list.add("Qu");
            } else{
                list.add(Character.toString(c)); // Add consonant
            }
        }

        // Loop through the vowelCount and add a random vowel
        for (int i = 0; i < vowelCount; i++) {
            list.add(Character.toString(vowels.charAt(r.nextInt(vowels.length())))); // Add vowel
        }

        // Shuffle the list to randomize the board
        Collections.shuffle(list);
    }
}
