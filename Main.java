import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws IOException {

        Time time = new Time();

        if(args.length==0){
            System.err.println("No Path provided");
            System.exit(0);
        }

        Time startCrawlTime = new Time();
        
        Crawler crawler = new Crawler(args[0]);
        Search searchObject = new Search(crawler.crawl());
        
        Time endCrawlTime = new Time();

        System.err.println();
        System.err.println("Crawling and Indexing took "+time.diff(startCrawlTime,endCrawlTime));
        System.err.println();


        Scanner sc = new Scanner(System.in);

        do{
            System.err.print("Enter a word to be searched = ");
            String word = sc.nextLine();

            word = word.toLowerCase();
            if(word.equals("end")) break;

            System.err.println("--------------------------------------------------------");

            Time searchStartTime = new Time();
            List<Data> results = searchObject.search(word);
            Time searchEndTime = new Time();
            

            Time rankStartTime = new Time();
            Collections.sort(results,(a,b)->Integer.compare(b.freq(), a.freq()));
            Time rankEndTime = new Time();

            if(results.isEmpty()){
                System.err.println("Nothing Found");
                continue;
            }

            int i=0;
            while(i<results.size() && i<3){
                System.err.println((i+1)+". Path = "+results.get(i).docId()+" Occuring = "+results.get(i).freq());
                i+=1;
            }
            
            System.err.println("Search Query took "+time.diff(searchStartTime, searchEndTime));
            System.err.println("Ranking took "+time.diff(rankStartTime, rankEndTime));

            System.err.println("--------------------------------------------------------");

        }while(true);

        sc.close();
    }
}
