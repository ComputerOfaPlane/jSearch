import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Search {

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        do{
            System.err.print("Enter a word to be searched = ");
            String word = sc.nextLine();
            if(word.toLowerCase().equals("end")) break;
            System.err.println("--------------------------------------------------------");
            searchWord(word);
            System.err.println("--------------------------------------------------------");
        }while(true);

        sc.close();
    }
    public static void searchWord(String toSearch) throws IOException{
        final Time start = new Time(System.nanoTime());

        File folder = new File("D:/stressTest/");

        Queue<String> queueOfPaths = new LinkedList<>();
        findFiles(folder,queueOfPaths);

        final Time mid = new Time(System.nanoTime());

        PriorityQueue<Data> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(b.counter, a.counter);
        });

        for(String path:queueOfPaths){
            int counter = 0;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line=reader.readLine())!=null){
                line = line.toLowerCase();
                String word = "";
                for(int i=0; i<line.length(); i++){
                    char ch = line.charAt(i);
                    if(ch<='z' && ch>='a') word = word + ch;
                    else{
                        if(word.equals(toSearch)) counter = counter + 1;
                        word = "";
                    }
                }
            }
            
            pq.offer(new Data(path, counter));
            reader.close();
        }
        int displayMax = 3;
        while(!pq.isEmpty() && displayMax-->0) System.out.println("Rank "+Math.abs(displayMax-3)+" = "+pq.poll().path);

        final Time end = new Time(System.nanoTime());

        System.err.println();
        System.err.println("Recursion: "+ new Time().diff(start, mid));
        System.err.println("Search: "+ new Time().diff(end, mid));
    }
    public static void findFiles(File folder, Queue<String> queueOfPaths){
        File files[] = folder.listFiles();
        if(files==null) return;
        for(File file:files){
            if(file.isDirectory()) findFiles(file, queueOfPaths);
            else if(file.isFile() && file.canRead()) queueOfPaths.offer(file.getAbsolutePath());
        }
    }
}