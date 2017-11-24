package com.example.nockanakalinowej.Utils;


/**
 * Created by AndrzejBulatek on 2017-11-11.
 */

public class Cycle {
    private int cycleLength;
    private int[] cycle;

    public Cycle(int _cycleLenght){
        cycleLength = _cycleLenght;
        cycle = new int[cycleLength];
    }

    public void permute(int[] array){
        int[] tmp = array.clone();
        for(int i = 0; i < (cycleLength - 1); i++){
            for(int j = 0; j < array.length; j++){
                if(array[j] == cycle[i]){
                    tmp[j] = cycle[i + 1];
                    break;
                }
            }
        }
        for(int j = 0; j < array.length; j++){
            if(array[j] == cycle[cycleLength - 1]){
                tmp[j] = cycle[0];
            }
        }
        for(int i=0; i < tmp.length; i++)
            array[i] = tmp[i];
    }

    public int getCycleLength(){
        return cycleLength;
    }

    public void setCycleLength(int _cycleLength) {
        cycleLength = _cycleLength;
        int[] tmp = new int[cycleLength];
        for(int i = 0; i < cycleLength; i++)
            tmp[i] = cycle[i];
        cycle = tmp;

    }
    public int[] getCycle(){
        return cycle;
    }
    public void setCycle(int index, int value){
        cycle[index] = value;
    }
}