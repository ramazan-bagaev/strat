/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation.TerrainGenerator;

import Utils.Geometry.Index;
import java.util.Random;
/**
 *
 * @author grapf
 */
public class BorderIterator {
    private int x0;
    private int y0;
    private double dx;
    private double dy;
    private int step;
    private int mm;
    private Index iterIndex;
    private Random random;

    private Index posA;
    private Index posB;

    public BorderIterator(TerrainEdge edge, Random random){
        this.posA = edge.getFirst().getPos();
        this.posB = edge.getSecond().getPos();
        this.random = random;
        init();
    }

    private void init(){
        x0 = posA.x;
        y0 = posA.y;
        dy = posB.y - posA.y;
        dx = posB.x - posA.x;
        step = 0;
        iterIndex = new Index(posA.x, posA.y);
    }

    public boolean hasNext() {
        if (iterIndex.equals(posB)) return false;
        return true;
    }

    public Index nextSameIndex() {
        
        dx = posB.x - iterIndex.x;
        dy = posB.y - iterIndex.y;
        
        
        if (dx != 0 & dy != 0){
            mm = multip(random);
            if (random.nextInt(100) < (Math.abs(dx) * 100/(Math.abs(dx) + Math.abs(dy)))){
                if (mm > 0){
                if (dx > 0){
                    iterIndex.x += 1;
                }
                else{
                    iterIndex.x -= 1;
                }
                mm -= 1;
            }
                 }
            else{
                if (mm > 0){
                if (dy > 0){
                    iterIndex.y += 1;
                }
                else{
                    iterIndex.y -= 1;
                }
                mm -= 1;
                }
        }
        }
        
        if (dx == 0){
            if (dy > 0){
                    iterIndex.y += 1;
                }
                else{
                    iterIndex.y -= 1;
                }
        }
        if (dy == 0){
            if (dx > 0){
                    iterIndex.x += 1;
                }
                else{
                    iterIndex.x -= 1;
                }
        }
        return iterIndex;
    }
    
    private int multip(Random rand){
        int randm = random.nextInt(4);
        switch(randm){
            case 2: return 2;
            case 3: return 3;
        }
        return 1;
    }
    
    
}
