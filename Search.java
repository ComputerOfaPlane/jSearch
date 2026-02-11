import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search {

    private Indexer index;

    Search(Indexer index){
        this.index = index;
    }

    public List<Data> search(String word){
        List<Data> results = new ArrayList<>();
        Map<String,Integer> map = index.getIndexMap().get(word);
        if(map == null) return results;
        for(String path:map.keySet()) results.add(new Data(path, map.get(path)));
        return results;
    }
}
