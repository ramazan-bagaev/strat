package Utils;

public class Interval {

    public int first;
    public int second;

    public Interval(int first, int second){
        this.first = first;
        this.second = second;
    }


    public void shift(int shift){
        first = first + shift;
        second = second + shift;
    }

    public int length(){
        return second - first + 1;
    }
}
