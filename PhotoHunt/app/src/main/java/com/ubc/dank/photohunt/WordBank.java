package com.ubc.dank.photohunt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Joey on 2/28/16.
 */
public class WordBank {
    private String[] words = {"ball","bat","bed","book","boy","bun","can","cake","cap","car","cat","cow","cub","cup","dad","day","dog","doll","dust","fan","feet","girl","gun","hall","hat","hen","jar","kite","man","map","men","mom","pan","pet","pie","pig","pot","rat","son","sun","toe","tub","van","apple","arm","banana","bike","bird","book","chin","clam","class","clover","club","corn","crayon","crow","crown","crowd","crib","desk","dime","dirt","dress","fang ","field","flag","flower","fog","game","heat","hill","home","horn","hose","joke","juice","kite","lake","maid","mask","mice","milk","mint","meal","meat","moon","mother","morning","name","nest","nose","pear","pen","pencil","plant","rain","river","road","rock","room","rose","seed","shape","shoe","shop","show","sink","snail","snake","snow","soda","sofa","star","step","stew","stove","straw","string","summer","swing","table","tank","team","tent","test","toes","tree","vest","water","wing","winter","woman","women","alarm","animal","aunt","bait","balloon","bath","bead","beam","bean","bedroom","boot","bread","brick","brother","camp","chicken","children","crook","deer","dock","doctor","downtown","drum","dust","eye","family","father","fight","flesh","food","frog","goose","grade","grandfather","grandmother","grape","grass","hook","horse","jail","jam","kiss","kitten","light","loaf","lock","lunch","lunchroom","meal","mother","notebook","owl","pail","parent","park","plot","rabbit","rake","robin","sack","sail","scale","sea","sister","soap","song","spark","space","spoon","spot","spy","summer","tiger","toad","town","trail","tramp","tray","trick","trip","uncle","vase","winter","water","week","wheel","wish","wool","yard","zebra"};
    private int size;

    public WordBank(){
        size = words.length;
    }

    /**
     * Returns n random words from the word bank. Will not return the same words more than once.
     * @param n the number of words to return from the word bank; n cannot be larger than the word bank size
     * @return a List of words from the word bank; no words will appear more than once
     */
    public List<String> getWords(int n){

        List<String> subset = new ArrayList<String>();
        Random r = new Random();

        for(int i = 0; i < n;i ++) {
            subset.add(words[r.nextInt(words.length)]);
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
