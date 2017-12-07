package Utils;

import java.util.ArrayList;

public class Vector {

    private float[] vector;
    private int n;

    public Vector(int n){
        this.n = n;
        vector = new float[n];
    }

    public Vector(int n, float[] vector){
        this.vector = vector;
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public float[] getVector() {
        return vector;
    }

    public Float get(int i){
        return vector[i];
    }

    public void set(int i, float num){
        vector[i] =  num;
    }

    public void print(){
        for (int i = 0; i < n; i++) System.out.print(vector[i] + " ");
    }
}
