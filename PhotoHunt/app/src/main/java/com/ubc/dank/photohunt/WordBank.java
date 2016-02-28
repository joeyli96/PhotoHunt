package com.ubc.dank.photohunt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Joey on 2/28/16.
 */
public class WordBank {
    private ArrayList<String> words;
    private int size;

    public WordBank(){
        words = new ArrayList<String>();
        File file = new File("nouns.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                words.add(line);
            }
            br.close();
        } catch (IOException e){
            // Error handling
        }
        size = words.size();
    }

    /**
     * Returns n random words from the word bank. Will not return the same words more than once.
     * @param n the number of words to return from the word bank; n cannot be larger than the word bank size
     * @return a List of words from the word bank; no words will appear more than once
     */
    public List<String> getWords(int n){
        List<String> subset = new ArrayList<String>();
        Random r = new Random();
        for (int i = 0; i < n; i++){
            String line;
            do {
                int j = r.nextInt(size);
                line = words.get(j);
            } while (subset.contains(line));
            subset.add(line);
        }
        return subset;
    }

    /**
     * Returns the number of entries in the word bank.
     * @return the number of entries in the word bank
     */
    public int getSize(){
        return size;
    }
}
