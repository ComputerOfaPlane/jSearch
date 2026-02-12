package com.grizzly.jsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexer{

    // words -> [Document : [a, b, c, d, e ......... list of positions]]
    private Map<String, Map<String,List<Integer>>> inverted_index;

    Indexer(){
        this.inverted_index = new HashMap<>();
    }

    public synchronized void add(String word, String docId, List<Integer> position){

        if(inverted_index.containsKey(word)){
            Map<String,List<Integer>> innerMap = inverted_index.get(word);
            if(innerMap.containsKey(docId)) innerMap.get(docId).addAll(position);
            else{
                innerMap.put(docId,new ArrayList<>());
                innerMap.get(docId).addAll(position);
            }
        }else{
            inverted_index.put(word, new HashMap<>());
            Map<String,List<Integer>> innerMap = inverted_index.get(word);
            innerMap.put(docId,new ArrayList<>());
            innerMap.get(docId).addAll(position);
        }
    }

    public Map<String, Map<String,List<Integer>>> getIndexMap() { return inverted_index; }
}
