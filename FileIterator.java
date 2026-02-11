import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class FileIterator {

    private Queue<File> queue = new LinkedList<>();

    FileIterator(String path){
        File root = new File(path);
        if (root.exists()) {
            queue.offer(root);
        }
    }

    public String nextPath(){ // lazy BFS
        
        while (!queue.isEmpty()) {

            File file = queue.poll();
            if (file == null) continue;

            if (file.isFile() && !file.isHidden() && file.canRead()) {
                return file.getAbsolutePath();
            }

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        queue.offer(f);
                    }
                }
            }
        }

        return null; // no more files
    }
}