package com.grizzly.jsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileIndexer {

    private Indexer index;
    private Word word;

    FileIndexer(Indexer index){
        this.index = index;
        word = new Word();
    }

    public void indexFile(String file) throws IOException{
        Map<String, Data> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int counter = 0;
        while((line=reader.readLine())!=null){
            for(String words:word.parse(line)){
                if(map.containsKey(words)) map.get(words).add(++counter);
                else{
                    map.put(words,new Data(file));
                    map.get(words).add(++counter);
                }
            }
        }
        reader.close();
        for(String key:map.keySet()){
            index.add(key, map.get(key).docId(), map.get(key).positions());
        }
    }
}