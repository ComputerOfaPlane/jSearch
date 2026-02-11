public class Data{

    private String path; 
    private int freq;

    Data(String path, int freq){
        this.path = path;
        this.freq = freq;
    }

    public void increment() {this.freq += 1;}

    public String docId(){return this.path;}

    public int freq(){return this.freq;}

}