package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        String text;
        StatObject so = this.readFile();

        // sorting structure should be implemented here.
        List<Integer> indexRankListOfScores = new ArrayList();

        for(int i = 0; i < so.finalScores.size(); i++){
            if(indexRankListOfScores.size() == 0){
                indexRankListOfScores.add(0);
                continue;
            }
            int bestScoreIndex = indexRankListOfScores.get(0);
            int bestScore = so.finalScores.get(bestScoreIndex);
            int currentScore = so.finalScores.get(i);
            if(currentScore > bestScore){
                indexRankListOfScores.add(0, i);
            } else{
                int listPosition = 1;
                while (listPosition < indexRankListOfScores.size() && currentScore <= bestScore) {
                    bestScoreIndex = indexRankListOfScores.get(listPosition++);
                    bestScore = so.finalScores.get(bestScoreIndex);
                }
                indexRankListOfScores.add(listPosition - 1, i);
            }

        }
        TextView firstScore = (TextView) findViewById(R.id.textView24);
        TextView secondScore = (TextView) findViewById(R.id.textView10);
        TextView thirdScore = (TextView) findViewById(R.id.textView16);
        TextView fourthScore = (TextView) findViewById(R.id.textView20);

        TextView firstResTimes = (TextView) findViewById(R.id.textView25);
        TextView secondResTimes = (TextView) findViewById(R.id.textView11);
        TextView thirdResTimes = (TextView) findViewById(R.id.textView17);
        TextView fourthResTimes = (TextView) findViewById(R.id.textView21);

        TextView firstWordCount = (TextView) findViewById(R.id.textView26);
        TextView secondWordCount = (TextView) findViewById(R.id.textView12);
        TextView thirdWordCount = (TextView) findViewById(R.id.textView18);
        TextView fourthWordCount = (TextView) findViewById(R.id.textView22);

        int index = 0;
        if(indexRankListOfScores.size() > 0){
            index = indexRankListOfScores.get(0);
            firstScore.setText(so.finalScores.get(index).toString());
            firstResTimes.setText(so.resetCounts.get(index).toString());
            firstWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 1){
            index = indexRankListOfScores.get(1);
            secondScore.setText(so.finalScores.get(index).toString());
            secondResTimes.setText(so.resetCounts.get(index).toString());
            secondWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 2){
            index = indexRankListOfScores.get(2);
            thirdScore.setText(so.finalScores.get(index).toString());
            thirdResTimes.setText(so.resetCounts.get(index).toString());
            thirdWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 3){
            index = indexRankListOfScores.get(3);
            fourthScore.setText(so.finalScores.get(index).toString());
            fourthResTimes.setText(so.resetCounts.get(index).toString());
            fourthWordCount.setText(so.wordCounts.get(index).toString());
        }

        TextView firstWord = (TextView) findViewById(R.id.word1);
        TextView secondWord = (TextView) findViewById(R.id.word2);
        TextView thirdWord = (TextView) findViewById(R.id.word3);

        List<String> rank = new ArrayList();

        for(Map.Entry entry: so.allWordsMap.entrySet()){
            if(rank.size() == 0){
                rank.add(entry.getKey().toString());
                continue;
            }
            String first = rank.get(0);
            int frequency = Integer.valueOf(entry.getValue().toString());
            int firstFrequency =  so.allWordsMap.get(first);
            if( frequency > firstFrequency){
                rank.add(0, entry.getKey().toString());
            } else if (frequency == firstFrequency){
                rank.add(1, entry.getKey().toString());
            }
        }

        TextView firstWordStat = (TextView) findViewById(R.id.word10);
        TextView secondWordStat = (TextView) findViewById(R.id.word20);
        TextView thirdWordStat = (TextView) findViewById(R.id.word30);

        String word = "";
        if(rank.size() > 0){
            word = rank.get(0);
            firstWord.setText(word);
            firstWordStat.setText(so.allWordsMap.get(word).toString());
        }
        if(rank.size() > 1){
            word = rank.get(1);
            secondWord.setText(word);
            secondWordStat.setText(so.allWordsMap.get(word).toString());
        }
        if(rank.size() > 2){
            word = rank.get(2);
            thirdWord.setText(word);
            thirdWordStat.setText(so.allWordsMap.get(word).toString());
        }
    }

    public void handleClick(View view) {
        TextView settingMinute = (TextView) findViewById(R.id.textView4);
        TextView settingSize = (TextView) findViewById(R.id.textView9);
        if (view.getId()== R.id.button4){
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 1){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            text = so.minutesList.get(so.minutesList.size() - 1).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 1).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button5) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 2){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            text = so.minutesList.get(so.minutesList.size() - 2).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 2).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button6) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 3){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            text = so.minutesList.get(so.minutesList.size() - 3).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 3).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button7) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 4){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            text = so.minutesList.get(so.minutesList.size() - 4).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 4).toString();
            settingSize.setText(text);
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
            stat =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return stat;
    }

}
