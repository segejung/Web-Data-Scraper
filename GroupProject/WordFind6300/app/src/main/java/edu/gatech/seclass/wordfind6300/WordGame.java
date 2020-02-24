package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.WindowManager;

import java.util.Arrays;


public class WordGame extends AppCompatActivity {
    GridView board;

    int numberOfMinutes, boardSize;

    //static final String[] letters = new String[] {
    //        "A", "B", "C", "D", "E",
    //        "F", "G", "H", "I", "J",
    //        "K", "L", "M", "N", "O",
    //        "P", "Q", "R", "S", "T",
    //        "U", "V", "W", "X", "Y", "Z"};

    String[] letters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Intent intent = getIntent();

        numberOfMinutes = intent.getIntExtra("minutes", 3);
        boardSize = intent.getIntExtra("boardSize", 4);

        letters = new String[boardSize*boardSize];

        Arrays.fill(letters, "A");

        board = findViewById(R.id.boardGrid);
        board.setNumColumns(boardSize);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, letters);

        board.setAdapter(adapter);

        board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
