import java.io.IOException;

public class Crawler {

    private String rootPath;

    Crawler(String rootPath){
        this.rootPath = rootPath;
    }

    public Indexer crawl() throws IOException{

        FileIterator fileIterator = new FileIterator(this.rootPath);

        Indexer index = new Indexer();

        FileIndexer fileIndexer = new FileIndexer(index);

        while(true){
            String path = fileIterator.nextPath();
            if(path==null) break;
            fileIndexer.indexFile(path);
        }

        return index;
    }
}