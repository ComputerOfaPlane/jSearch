public class Time {

    private int minutes; double seconds;

    Time(){
        long nanoseconds = System.nanoTime();
        this.seconds = nanoseconds/1e9;
        this.minutes = (int) (seconds/60);
        this.seconds = this.seconds - minutes*60;
    }

    Time(int minutes, double seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString(){
        return this.minutes+"m "+String.format("%.2f",this.seconds)+"s";
    }

    public Time diff(Time time1, Time time2){
        return new Time(Math.abs(time1.minutes-time2.minutes), Math.abs(time1.seconds-time2.seconds));
    }
}
