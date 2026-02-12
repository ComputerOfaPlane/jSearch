import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search {

    private Indexer index;
    private Map<String, Map<String,List<Integer>>> map;
    private int queryWords;

    Search(Indexer index){
        this.index = index;
        this.map = new HashMap<>();
    }

    Search(){
        this.map = new HashMap<>();
    }

    // find the words and invert the inverted index
    public void search(String query){
        this.map.clear();
        Word object = new Word();
        List<String> words = object.parse(query);
        this.queryWords = words.size();

        for(String w:words){
            Map<String, List<Integer>> documentPositionMap = index.getIndexMap().get(w);
            if(documentPositionMap == null) continue;

            for(String docId: documentPositionMap.keySet()){

                if(map.containsKey(docId)){
                    if(map.get(docId).containsKey(w)) map.get(docId).get(w).addAll(documentPositionMap.get(docId));
                    else map.get(docId).put(w,documentPositionMap.get(docId));
                }
                else{
                    map.put(docId, new HashMap<>());
                    map.get(docId).put(w,new ArrayList<>());
                    map.get(docId).get(w).addAll(documentPositionMap.get(docId));
                }
            }
        }
    }

    public Map<String, Map<String,List<Integer>>> get(){ return this.map; }

    public int queryWords(){ return queryWords; }
}
