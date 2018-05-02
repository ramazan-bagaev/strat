package Foundation.Army;

import java.util.ArrayList;

public class FrontLine {

    private ArrayList<FrontLinePiece> frontLinePieces;

    private static int size;

    // at least one frontLinePiece should be
    public FrontLine(ArrayList<FrontLinePiece> frontLinePieces, int size){
        this.frontLinePieces = frontLinePieces;
        this.size = size;
    }

    public FrontLinePiece getFrontLinePiece(int index){
        for(FrontLinePiece frontLinePiece: frontLinePieces){
            if (frontLinePiece.pos == index) return frontLinePiece;
        }
        return null;
    }

    public static void fight(FrontLine a, FrontLine b){
        FrontLinePiece last1 = a.frontLinePieces.get(0);
        FrontLinePiece last2 = b.frontLinePieces.get(0);
        for(int i = 0; i < size; i++){
            FrontLinePiece p1 = a.getFrontLinePiece(i);
            FrontLinePiece p2 = b.getFrontLinePiece(i);
            if (p1 == null && p2 == null) continue;
            if (p2 != null) last2 = p2;
            if (p1 != null) last1 = p1;
            FrontLinePiece.fight(last1, last2);
        }
    }
}
