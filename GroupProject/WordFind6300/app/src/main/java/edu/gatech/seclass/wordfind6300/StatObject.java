package edu.gatech.seclass.wordfind6300;

import android.app.Application;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StatObject extends Application implements Serializable {
    List<Integer> finalScores;
    List<Integer> resetCounts;
    List<Integer> wordCounts;
    List<Integer> sizeList;
    List<Integer> minutesList;
    Set<String> highestWordsList;

    int finalScore = 10;
    int resetCount = 2;
    int wordCount = 10;
    int size = 4;
    int minutes = 3;
    String highestWord = "";
    public StatObject(){
        finalScores = new ArrayList();
        resetCounts = new ArrayList();
        wordCounts = new ArrayList();
        sizeList = new ArrayList();
        minutesList = new ArrayList();
        highestWordsList = new ArrayList();
        saveAllToList();

        finalScore = 20;
        resetCount = 5;
        size = 5;
        minutes = 3;
        highestWord = "cat";
        saveAllToList();

        finalScore = 40;
        resetCount = 20;
        size = 6;
        minutes = 2;
        highestWord = "apple";
        saveAllToList();

        finalScore = 30;
        resetCount = 15;
        size = 7;
        minutes = 1;
        highestWord = "banana";
        saveAllToList();

    }
    public void updateScores(int finalScore){
        this.finalScore = finalScore;
    }
    public void updateResetCounts(int resetCount){
        this.resetCount = resetCount;
    }
    public void updateWordCounts(int wordCount){
        this.wordCount = wordCount;
    }
    public void updateSizeList(int size){
        this.size = size;
    }
    public void updateMinutes(int minutes){
        this.minutes = minutes;
    }
    public void updateHighestWord(String highestWord){
        this.highestWord = highestWord;
    }
    public void saveAllToList(){
        finalScores.add(finalScore);
        resetCounts.add(resetCount);
        wordCounts.add(wordCount);
        sizeList.add(size);
        minutesList.add(minutes);
        highestWordsList.add(highestWord);
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
//            displayText.setText("the last value stored in the highestWordsList is \n" + stat.highestWordsList.get(stat.highestWordsList.size() - 1));
        } catch(IOException ex){
            Toast.makeText(getApplicationContext(), "IOException is caught, returning a new object", Toast.LENGTH_SHORT).show();
            stat =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return stat;
    }
}
