package com.grizzly.jsearch;

public class Time {

    private final long nanoTime;

    Time(){
        this.nanoTime = System.nanoTime();
    }

    Time(long nanoTime){
        this.nanoTime = nanoTime;
    }

    @Override
    public String toString(){
        long totalSeconds = nanoTime / 1_000_000_000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return minutes + "m " + seconds + "s";
    }

    public Time diff(Time start, Time end){
        return new Time(end.nanoTime - start.nanoTime);
    }
}
