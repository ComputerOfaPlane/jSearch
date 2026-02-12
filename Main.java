import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws IOException {

        Time time = new Time();

        if(args.length==0){
            System.err.println("No Path provided");
            System.exit(0);
        }
        int maxDisplay = 3;
        if(args.length>1) maxDisplay = Integer.parseInt(args[1]);

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
            String query = sc.nextLine();

            query = query.toLowerCase();
            if(query.equals("end")) break;

            System.err.println("--------------------------------------------------------");

            Time searchStartTime = new Time();
            
            searchObject.search(query);
            Ranker ranker = new Ranker(searchObject);
            ranker.rank();
            List<Rank> results = ranker.get();

            if(results.isEmpty()){
                System.err.println("Nothing Found");
                continue;
            }

            int i=0;
            while(i<results.size() && i<maxDisplay){
                System.err.println((i+1)+". Path = "+results.get(i).path());
                i+=1;
            }

            Time searchEndTime = new Time();
            
            System.err.println("Search Query took "+time.diff(searchStartTime, searchEndTime));

            System.err.println("--------------------------------------------------------");

        }while(true);

        sc.close();
    }
}
