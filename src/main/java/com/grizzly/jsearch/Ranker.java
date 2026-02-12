package com.grizzly.jsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Ranker {

    private List<Rank> results;
    private Search search;

    Ranker(Search search){
        this.results = new ArrayList<>();
        this.search = search;
    }

    public void rank(){

        Map<String, Map<String,List<Integer>>> documentWordPositions = search.get();

        PriorityQueue<Rank> pq = new PriorityQueue<>((a,b)->Double.compare(b.score(), a.score()));

        int maxWords = search.queryWords();

        for(String document:documentWordPositions.keySet()){
            
            Map<String, List<Integer>> innerMap = documentWordPositions.get(document);

            for(String word:innerMap.keySet()) Collections.sort(innerMap.get(word));
        }

        for(String document:documentWordPositions.keySet()){

            Map<String, List<Integer>> innerMap = documentWordPositions.get(document);

            int totalFreq = 0;
            int matchedWords = innerMap.size();

            // If no words matched, skip
            if (matchedWords == 0) continue;

            // Collect all position lists
            List<List<Integer>> positionLists = new ArrayList<>(innerMap.values());

            // Initialize pointers (one per word)
            int k = positionLists.size();
            int[] pointers = new int[k];

            int span = Integer.MAX_VALUE;

            // Minimum range covering k sorted lists
            while (true) {

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int minListIndex = -1;

                for (int i = 0; i < k; i++) {

                    if (pointers[i] >= positionLists.get(i).size()) {
                        minListIndex = -1; // exhausted one list
                        break;
                    }

                    int value = positionLists.get(i).get(pointers[i]);

                    if (value < min) {
                        min = value;
                        minListIndex = i;
                    }

                    if (value > max) {
                        max = value;
                    }
                }

                if (minListIndex == -1) break;

                span = Math.min(span, max - min);

                // advance pointer of list that had minimum value
                pointers[minListIndex]++;
            }

            // Frequency calculation
            for (List<Integer> list : positionLists) {
                totalFreq += list.size();
            }

            // --- SCORING ---

            double closeness = 1.0 / (span + 1.0);

            double coverage = matchedWords / (double) maxWords;

            double frequency = Math.log(1 + totalFreq);

            double score =
                    0.75 * closeness +
                    0.20 * coverage +
                    0.05 * frequency;

            pq.offer(new Rank(document, score));
        }

        while(!pq.isEmpty()) this.results.add(pq.poll());
    }

    public List<Rank> get(){ return this.results; }
}
