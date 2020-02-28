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

        if (getIntent().hasExtra("edu.gatech.seclass.gameapp.SOMETHING")) {
//            TextView tv = (TextView) findViewById(R.id.textView5);
//            //TEST: the following is for test!!!!!
//            String text = getIntent().getExtras().getString("edu.gatech.seclass.gameapp.SOMETHING");
//            StatObject so = this.readFile();
//
//            tv.setText(text);
//            System.out.print(so.allWordsMap);
//            text = so.allWordsMap.get(so.allWordsMap.size() - 2);
//            tv.setText(text);

        }
        String text;
        StatObject so = this.readFile();

        // sorting structure should be implemented here.

        TextView firstScore = (TextView) findViewById(R.id.textView24);
        TextView secondScore = (TextView) findViewById(R.id.textView10);
        TextView thirdScore = (TextView) findViewById(R.id.textView16);
        TextView fourthScore = (TextView) findViewById(R.id.textView20);

        text = so.finalScores.get(so.finalScores.size() - 1).toString();
        firstScore.setText(text);
        text = so.finalScores.get(so.finalScores.size() - 2).toString();
        secondScore.setText(text);
        text = so.finalScores.get(so.finalScores.size() - 3).toString();
        thirdScore.setText(text);
        text = so.finalScores.get(so.finalScores.size() - 4).toString();
        fourthScore.setText(text);

        TextView firstResTimes = (TextView) findViewById(R.id.textView25);
        TextView secondResTimes = (TextView) findViewById(R.id.textView11);
        TextView thirdResTimes = (TextView) findViewById(R.id.textView17);
        TextView fourthResTimes = (TextView) findViewById(R.id.textView21);

        text = so.resetCounts.get(so.resetCounts.size() - 1).toString();
        firstResTimes.setText(text);
        text = so.resetCounts.get(so.resetCounts.size() - 2).toString();
        secondResTimes.setText(text);
        text = so.resetCounts.get(so.resetCounts.size() - 3).toString();
        thirdResTimes.setText(text);
        text = so.resetCounts.get(so.resetCounts.size() - 4).toString();
        fourthResTimes.setText(text);

        TextView firstWordCount = (TextView) findViewById(R.id.textView26);
        TextView secondWordCount = (TextView) findViewById(R.id.textView12);
        TextView thirdWordCount = (TextView) findViewById(R.id.textView18);
        TextView fourthWordCount = (TextView) findViewById(R.id.textView22);

        text = so.wordCounts.get(so.wordCounts.size() - 1).toString();
        firstWordCount.setText(text);
        text = so.wordCounts.get(so.wordCounts.size() - 2).toString();
        secondWordCount.setText(text);
        text = so.wordCounts.get(so.wordCounts.size() - 3).toString();
        thirdWordCount.setText(text);
        text = so.wordCounts.get(so.wordCounts.size() - 4).toString();
        fourthWordCount.setText(text);


        TextView firstWord = (TextView) findViewById(R.id.word1);
        TextView secondWord = (TextView) findViewById(R.id.word2);
        TextView thirdWord = (TextView) findViewById(R.id.word3);

        List<String> rank = new ArrayList();

        for(Map.Entry entry: so.allWordsMap.entrySet()){
            if(rank.size() == 0){
                rank.add(entry.getKey().toString());
                continue;
            }
            for(int i = 0; i < rank.size(); i++){
                String first = rank.get(0);
                int frequency = Integer.valueOf(entry.getValue().toString());
                int firstFrequency =  so.allWordsMap.get(first);
                if( frequency > firstFrequency){
                    rank.add(0, entry.getKey().toString());
                } else if (frequency == firstFrequency){
                    rank.add(1, entry.getKey().toString());
                }
            }
        }

        if(rank.size() > 0){
            firstWord.setText(rank.get(0) == null ? "" : rank.get(0));
        }
        if(rank.size() > 1){
            secondWord.setText(rank.get(1) == null ? "" : rank.get(1));
        }
        if(rank.size() > 2){
            thirdWord.setText(rank.get(2) == null ? "" : rank.get(2));
        }


        TextView firstWordStat = (TextView) findViewById(R.id.word10);
        TextView secondWordStat = (TextView) findViewById(R.id.word20);
        TextView thirdWordStat = (TextView) findViewById(R.id.word30);

        if(so.wordCounts.size() > 0){
            text = so.wordCounts.get(so.wordCounts.size() - 1).toString();
            firstWordStat.setText(text);
        }
        if(so.wordCounts.size() > 1){
            text = so.wordCounts.get(so.wordCounts.size() - 2).toString();
            secondWordStat.setText(text);
        }
        if(so.wordCounts.size() > 2) {
            text = so.wordCounts.get(so.wordCounts.size() - 3).toString();
            thirdWordStat.setText(text);
        }
    }




    public void handleClick(View view) {
        TextView settingMinute = (TextView) findViewById(R.id.textView4);
        TextView settingSize = (TextView) findViewById(R.id.textView9);
        if (view.getId()== R.id.button4){
            String text;
            StatObject so = this.readFile();
            text = so.minutesList.get(so.minutesList.size() - 1).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 1).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button5) {
            String text;
            StatObject so = this.readFile();
            text = so.minutesList.get(so.minutesList.size() - 2).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 2).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button6) {
            String text;
            StatObject so = this.readFile();
            text = so.minutesList.get(so.minutesList.size() - 3).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 3).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button7) {
            String text;
            StatObject so = this.readFile();
            text = so.minutesList.get(so.minutesList.size() - 4).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(so.sizeList.size() - 4).toString();
            settingSize.setText(text);
        }
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

            Toast.makeText(getApplicationContext(), "Data has been saved", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Didn't find saved data, initializing", Toast.LENGTH_SHORT).show();
            stat =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return stat;
    }

}
