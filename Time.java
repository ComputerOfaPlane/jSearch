public class Time {

    private int minutes; double seconds;

    Time(long nanoseconds){
        this.seconds = nanoseconds/1e9;
        this.minutes = (int) (seconds/60);
        this.seconds = this.seconds - minutes*60;
    }

    Time(){
        this.minutes = 0;
        this.seconds = 0.0;
    }

    @Override
    public String toString(){
        return this.minutes+"m "+this.seconds+"s";
    }

    public Time diff(Time time1, Time time2){
        Time obj = new Time();
        obj.minutes = Math.abs(time1.minutes-time2.minutes);
        obj.seconds = Math.abs(time1.seconds-time2.seconds);
        return obj;
    }
}
