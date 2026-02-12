package com.grizzly.jsearch;

import java.util.ArrayList;
import java.util.List;

public class Data{

    private String path; 
    private List<Integer> position;

    Data(String path){
        this.path = path;
        this.position = new ArrayList<>();
    }

    public void add(int i) {this.position.add(i);}

    public String docId(){return this.path;}

    public List<Integer> positions(){return this.position;}

}