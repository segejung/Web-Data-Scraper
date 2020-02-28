package edu.gatech.seclass.wordfind6300;

import android.app.Application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatObject extends Application implements Serializable {
    List<Integer> finalScores;
    List<Integer> resetCounts;
    List<Integer> wordCounts;
    List<Integer> sizeList;
    List<Integer> minutesList;
    List<Map<Character, Integer>> letterWeights;
    Map<String, Integer> allWordsMap;

    int finalScore = 0;
    int resetCount = 0;
    int wordCount = 0;
    int size = 4;
    int minutes = 3;
    String highestWord = "";
    Map<Character, Integer> letterWeight;

    public StatObject(){
        finalScores = new ArrayList();
        resetCounts = new ArrayList();
        wordCounts = new ArrayList();
        sizeList = new ArrayList();
        minutesList = new ArrayList();
        letterWeight = new HashMap();
        letterWeights = new ArrayList();
        allWordsMap = new HashMap();

        letterWeight.put('B', 2);
        letterWeights.add(letterWeight);
        saveAllToList();

        finalScore = 20;
        resetCount = 5;
        wordCount = 0;
        size = 5;
        minutes = 3;
        letterWeight.put('B', 5);
        letterWeights.add(letterWeight);
        saveAllToList();

        finalScore = 40;
        resetCount = 20;
        wordCount = 0;
        size = 6;
        minutes = 2;
        letterWeight.put('B', 3);
        letterWeights.add(letterWeight);
        saveAllToList();

        finalScore = 60;
        resetCount = 15;
        wordCount = 0;
        size = 7;
        minutes = 1;
        letterWeight.put('B', 4);
        letterWeights.add(letterWeight);
        saveAllToList();

        finalScore = 80;
        resetCount = 15;
        wordCount = 0;
        size = 4;
        minutes = 6;
        letterWeight.put('B', 1);
        letterWeights.add(letterWeight);
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
    }

}
