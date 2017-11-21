package com.example.nockanakalinowej.Utils;

        import android.util.Log;

        import java.util.ArrayList;

        import java.util.List;
        import java.util.Random;

        import static java.lang.Math.abs;

/**
 * Created by AndrzejBulatek on 2017-11-11.
 */

public class Permutation {
    private int[] permutation;      //tablica permutacji - indeksowi przyporządkowana jest wartość
    private Cycle[] cycles;         //tablica cykli tej permutacji

    public Permutation(int cyclesNo, int[] cyclesLenghts, int[] _domain){
        int[] domain= new int[_domain.length];
        for (int i=0; i<domain.length;i++)
            domain[i] = _domain[i];
        cycles = new Cycle[cyclesNo];
        permutation = _domain.clone();
        int tmp;
        int[] TMP;
        Random random = new Random();
        for (int i=0; i<cyclesNo;i++){
            cycles[i]=new Cycle(cyclesLenghts[i]);
            for (int j=0;j<cyclesLenghts[i];j++){
                tmp= abs(random.nextInt()) % domain.length;
                this.cycles[i].setCycle(j, domain[tmp]);
                domain[tmp]=domain[domain.length-1];
                TMP=new int[domain.length-1];
                for(int k = 0; k < (domain.length - 1); k++){
                    TMP[k] = domain[k];
                }
                domain = new int[TMP.length];
                for (int k=0; k<domain.length;k++)
                    domain[k] = TMP[k];
            }
        }
        for(int i=0; i<cyclesNo; i++){
            cycles[i].permute(this.permutation);
        }
    }

    public Cycle[] getCycles(){
        return this.cycles;
    }
    public int[] getPermutation(){
        return this.permutation;

    }

}