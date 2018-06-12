package Generation.TerrainGenerator;

import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Comparator;

public class PlateGenerator {

    private ArrayList<TerrainNode> nodes;
    private ArrayList<TerrainEdge> leftOrientation;
    private ArrayList<TerrainEdge> rightOrientation;

    private ArrayList<Plate> plates;

    public PlateGenerator(){
        leftOrientation = new ArrayList<>();
        rightOrientation = new ArrayList<>();
    }

    public void generate(TerrainMap terrainMap){
        nodes = terrainMap.getNodes();
        plates = new ArrayList<>();
        leftOrientation.clear();
        rightOrientation.clear();
        startCycles();
        terrainMap.setPlates(plates);
    }

    private void startCycles(){
        for(TerrainNode node: nodes){
            for(TerrainEdge edge: node.getEdges()) {
                startLeftCycle(node, edge);
                startRightCycle(node, edge);
            }
        }
    }

    private void startLeftCycle(TerrainNode node, TerrainEdge edge){
        Plate plate = new Plate();
        ArrayList<TerrainNode> plateNodes = new ArrayList<>();
        plateNodes.add(node);
        ArrayList<TerrainEdge> plateEdges = new ArrayList<>();
        plateEdges.add(edge);
        TerrainEdge curEdge = edge;
        TerrainNode curNode = edge.getOpposite(node);
        int count = 0;
        double angle = 0;
        while(true){
            count++;
            if (curNode == node) break;
            plateNodes.add(curNode);
            if (wasHere(curNode, curEdge, true)) return;
            setFlagged(curNode, curEdge, true);
            TerrainEdge newEdge = nextLeftEdge(curNode, curEdge);
            if (newEdge == null) return;
            Index dir1 = curNode.getPos().minus(curEdge.getOpposite(curNode).getPos());
            Index dir2 = newEdge.getOpposite(curNode).getPos().minus(curNode.getPos());
            angle = calculateDeviationAngle(angle, dir1, dir2, true);
            curEdge = newEdge;
            plateEdges.add(curEdge);
            curNode = curEdge.getOpposite(curNode);
            if (count >= nodes.size()) return;
        }
        if (angle <= 0) return;
        if (plate.setNodes(plateNodes)){
            plate.setEdges(plateEdges);
            if (plates.contains(plate)) return;
            plates.add(plate);
        }
    }

    private void startRightCycle(TerrainNode node, TerrainEdge edge){
        Plate plate = new Plate();
        ArrayList<TerrainNode> plateNodes = new ArrayList<>();
        plateNodes.add(node);
        ArrayList<TerrainEdge> plateEdges = new ArrayList<>();
        plateEdges.add(edge);
        TerrainEdge curEdge = edge;
        TerrainNode curNode = edge.getOpposite(node);
        int count = 0;
        double angle = 0;
        while(true){
            count++;
            if (curNode == node) break;
            plateNodes.add(curNode);
            if (wasHere(curNode, curEdge, false)) return;
            setFlagged(curNode, curEdge, false);
            TerrainEdge newEdge = nextRightEdge(curNode, curEdge);
            if (newEdge == null) return;
            Index dir1 = curNode.getPos().minus(curEdge.getOpposite(curNode).getPos());
            Index dir2 = newEdge.getOpposite(curNode).getPos().minus(curNode.getPos());
            angle = calculateDeviationAngle(angle, dir1, dir2, false);
            curEdge = newEdge;
            plateEdges.add(curEdge);
            curNode = curEdge.getOpposite(curNode);
            if (count >= nodes.size()) return;
        }
        if (angle <= 0) return;
        if (plate.setNodes(plateNodes)){
            plate.setEdges(plateEdges);
            if (plates.contains(plate)) return;
            plates.add(plate);
        }
    }

    private double calculateDeviationAngle(double curAngle, Index dirOld, Index dirNew, boolean leftOrientation){
        boolean curOrientLeft = onLeft(dirOld, dirNew);
        if ((leftOrientation && curOrientLeft) || (!leftOrientation && !curOrientLeft)){
            curAngle += Math.acos(cos(dirOld, dirNew));
        } else{
            curAngle -= Math.acos(cos(dirOld, dirNew));
        }
        return curAngle;
    }

    private TerrainEdge nextLeftEdge(TerrainNode secondNode, TerrainEdge edge){
        ArrayList<TerrainEdge> terrainEdges = new ArrayList<>(secondNode.getEdges());
        terrainEdges.remove(edge);
        if (terrainEdges.size() == 0) return null;
        Index edgeDir = secondNode.getPos().minus(edge.getOpposite(secondNode).getPos());
        Comparator<TerrainEdge> cmp = new Comparator<TerrainEdge>() {

            @Override
            public int compare(TerrainEdge t1, TerrainEdge t2) {
                Index dir1 = t1.getOpposite(secondNode).getPos().minus(secondNode.getPos());
                Index dir2 = t2.getOpposite(secondNode).getPos().minus(secondNode.getPos());
                if (onLeft(edgeDir, dir1)){
                    if (onLeft(edgeDir, dir2)) return (int)Math.signum(cos(edgeDir, dir1) - cos(edgeDir, dir2));
                    return -1;
                }
                if (onLeft(edgeDir, dir2)) return 1;
                return (int) Math.signum(cos(edgeDir, dir2) - cos(edgeDir, dir1));
            }
        };


        terrainEdges.sort(cmp);
        return terrainEdges.get(0);
    }

    private TerrainEdge nextRightEdge(TerrainNode secondNode, TerrainEdge edge){
        ArrayList<TerrainEdge> terrainEdges = new ArrayList<>(secondNode.getEdges());
        terrainEdges.remove(edge);
        if (terrainEdges.size() == 0) return null;
        Index edgeDir = secondNode.getPos().minus(edge.getOpposite(secondNode).getPos());
        Comparator<TerrainEdge> cmp = new Comparator<TerrainEdge>() {

            @Override
            public int compare(TerrainEdge t1, TerrainEdge t2) {
                Index dir1 = t1.getOpposite(secondNode).getPos().minus(secondNode.getPos());
                Index dir2 = t2.getOpposite(secondNode).getPos().minus(secondNode.getPos());
                if (!onLeft(edgeDir, dir1)){
                    if (!onLeft(edgeDir, dir2)) return (int)Math.signum(cos(edgeDir, dir1) - cos(edgeDir, dir2));
                    return -1;
                }
                if (!onLeft(edgeDir, dir2)) return 1;
                return (int) Math.signum(cos(edgeDir, dir2) - cos(edgeDir, dir1));
            }
        };


        terrainEdges.sort(cmp);
        return terrainEdges.get(0);
    }

    private static boolean onLeft(Index dir1, Index dir2){
        return (dir1.x * dir2.y - dir1.y * dir2.x <= 0);
    }

    private static double cos(Index dir1, Index dir2){
        return (double) (dir1.x * dir2.x + dir1.y * dir2.y)/(dir1.norm()*dir2.norm());
    }

    private boolean wasHere(TerrainNode node, TerrainEdge edge, boolean isCurrentOrientationLeft){
        boolean isCurrentDirectionForward = edge.getSecond().equals(node);
        if ((isCurrentDirectionForward && isCurrentOrientationLeft)
                || (!isCurrentDirectionForward && !isCurrentOrientationLeft)){
            if (leftOrientation.contains(edge)) return true;
            return false;
        }
        else{
            if (rightOrientation.contains(edge)) return true;
            return false;
        }
    }

    private void setFlagged(TerrainNode node, TerrainEdge edge, boolean isCurrentOrientationLeft){
        boolean isCurrentDirectionForward = edge.getSecond().equals(node);
        if ((isCurrentDirectionForward && isCurrentOrientationLeft)
                || (!isCurrentDirectionForward && !isCurrentOrientationLeft)){
            leftOrientation.add(edge);
        }
        else{
            rightOrientation.add(edge);
        }
    }


}
