import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Crawler {

    private String rootPath;

    Crawler(String rootPath){
        this.rootPath = rootPath;
    }

    public Indexer crawl() throws IOException{

        FileIterator fileIterator = new FileIterator(this.rootPath);

        Indexer index = new Indexer();

        FileIndexer fileIndexer = new FileIndexer(index);

        // int threads = Runtime.getRuntime().availableProcessors();
        int threads = 6;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        while(true){
            String path = fileIterator.nextPath();

            if(path==null) break;

            executor.submit(() -> {
                try {
                    fileIndexer.indexFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return index;
    }
}