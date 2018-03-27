package Utils;

import java.util.ArrayList;

public class Matrix {

    private float[][] table;
    private int n; // height
    private int m; // width

    public Matrix(int n, int m){
        this.n = n;
        this.m = m;
        table = new float[n][m];
    }

    public Matrix(int n, int m, float[][] table){
        this.n = n;
        this.m = m;
        this.table = table;
    }

    public void set(int i, int j, float num){
        table[i][j] = num;
    }

    public Vector multiply(Vector vector) {
        if (m != vector.getN()) return null;
        Vector result = new Vector(n);
        for (int i = 0; i < n; i++) {
            float sum = 0;
            for (int j = 0; j < m; j++) sum += table[i][j] * vector.get(j);
            result.set(i, sum);
        }
        return result;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(m, n);
        for (int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) result.set(j, i, table[i][j]);
        return result;
    }

    public Matrix reverse(){
        if (n != m) return null;
        Matrix result = new Matrix(n, n);

        return result;
    }

    public void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) System.out.print(table[i][j] + " ");
            System.out.print("\n");
        }
    }

    public static void main(String[] argv){
        float[][] m1 = new float[][]{
                {1,0,1,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        float[] v1 = new float[]{1, 0, 2, 0};
        Matrix matrix = new Matrix(4, 4, m1);
        Vector vector = new Vector(4, v1);
        Vector vector2 = matrix.multiply(vector);
        vector2.print();
    }
}
