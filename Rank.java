public class Rank {

    private String path;
    private double score;

    Rank(String path, double score){
        this.path = path;
        this.score = score;
    }

    public String path(){ return this.path; }

    public double score(){ return this.score; }
}
