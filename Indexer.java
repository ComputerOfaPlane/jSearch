import java.util.HashMap;
import java.util.Map;

public class Indexer{

    private Map<String, Map<String,Integer>> inverted_index; // words -> [Document : frequency]

    Indexer(){
        this.inverted_index = new HashMap<>();
    }

    public void add(String word, String docId, int frequency){

        if(inverted_index.containsKey(word)){
            inverted_index.get(word).put(docId, inverted_index.get(word).getOrDefault(docId, 0)+frequency);
        }else{
            Map<String,Integer> map = new HashMap<>();
            map.put(docId,frequency);
            inverted_index.put(word,map);
        }
    }

    public Map<String, Map<String,Integer>> getIndexMap() { return inverted_index; }
}
