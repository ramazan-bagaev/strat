package Generation.TerrainGenerator;

import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class TerrainMapGenerator {

    private Random random;
    private TerrainMap map;
    private Index size;

    public TerrainMapGenerator(Random random){
        this.random = random;
    }

    public TerrainMap generate(Index size, int number){
        this.size = size;
        this.map = new TerrainMap(size);
        generateTerrainNodes(number);
        randomlyLinkInteriorNodes(2, 3);
        return map;
    }

    private void generateTerrainNodes(int number){
        addRandomBorderNodes();
        createRandomUnlinkedNodes(number);
    }

    private void createRandomUnlinkedNodes(int number){
        for(int i = 0; i < number; i++){
            createRandomNode();
        }
    }

    private void addRandomBorderNodes(){
        addCornerNodes();
        addSideNode();
        linkBorderNodes(2);
    }

    private void addCornerNodes(){
        Index pos = new Index(0, 0);
        map.addBorderNode(new TerrainNode(pos));
        pos = new Index(size.x - 1, 0);
        map.addBorderNode(new TerrainNode(pos));
        pos = new Index(0, size.y - 1);
        map.addBorderNode(new TerrainNode(pos));
        pos = new Index(size.x - 1, size.y - 1);
        map.addBorderNode(new TerrainNode(pos));
    }

    private void addSideNode(){
        int number = random.nextInt(2)+1;
        Index pos;
        for(int i = 0; i < number; i++){
            int x = random.nextInt(size.x-42) + 21;
            pos = new Index(x, 0);
            if (isFree(pos)) map.addBorderNode(new TerrainNode(pos));
        }
        number = random.nextInt(2)+1;
        for(int i = 0; i < number; i++){
            int x = random.nextInt(size.x - 42) + 21;
            pos = new Index(x, size.y - 1);
            if (isFree(pos)) map.addBorderNode(new TerrainNode(pos));
        }

        number = random.nextInt(2)+1;

        for(int i = 0; i < number; i++){
            int y = random.nextInt(size.y - 42) + 21;
            pos = new Index(0, y);
            if (isFree(pos)) map.addBorderNode(new TerrainNode(pos));
        }

        number = random.nextInt(2) + 1;

        for(int i = 0; i < number; i++){
            int y = random.nextInt(size.y - 42) + 21;
            pos = new Index(size.x - 1, y);
            if (isFree(pos)) map.addBorderNode(new TerrainNode(pos));
        }

    }

    private void createRandomNode(){
        Index pos = new Index(0, 0);
        int count = 0;
        while(true) {
            count++;
            pos.x = random.nextInt(size.x);
            pos.y = random.nextInt(size.y);
            if (isFree(pos)) {
                map.addNode(new TerrainNode(new Index(pos)));
                return;
            }
            if (count > 100) return;
        }
    }

    private boolean isFree(Index pos){
        for(TerrainNode node: map.getNodes()){
            if (node.getPos().distance(pos) < 20){
                return false;
            }
        }
        double minDis = 300;
        for (TerrainEdge edge: map.getEdges() ){
            double res = edge.distance(pos);
            if (minDis > res) minDis = res;
            if (res < 15) return false;
        }
        return true;
    }

    private boolean isAllowed(TerrainEdge edge){
        for(TerrainEdge terrainEdge: map.getEdges()){
            if (terrainEdge.links(edge)){
                if (terrainEdge.equals(edge)) return false;
                continue;
            }
            if (terrainEdge.intersects(edge)) return false;
        }
        for(TerrainNode node: map.getNodes()){
            if (edge.getOpposite(node) != null) continue;
            if (edge.distance(node.getPos()) < 5) return false;
        }
        return true;
    }

    private void linkBorderNodes(int linksNumber){
        for(TerrainNode node: map.getBorderNodes()){
            int count = 0;
            ArrayList<TerrainNode> sortedNodes = getSortedByDistanceNodes(map.getBorderNodes(), node);
            for(TerrainNode sortedNode: sortedNodes){
                if (count > linksNumber - 1) break;
                Index delta = sortedNode.getPos().minus(node.getPos());
                if (delta.x != 0 && delta.y != 0) continue;
                TerrainEdge edge = new TerrainEdge(node, sortedNode);
                if (isAllowed(edge)){
                    map.addEdge(edge);
                    node.addEdge(edge);
                    sortedNode.addEdge(edge);
                    count++;
                }
            }
        }
    }

    private void randomlyLinkInteriorNodes(int minLinks, int maxLinks){
        for(TerrainNode node: map.getNodes()){
            if (map.getBorderNodes().contains(node)) continue;
            int count = 0;
            ArrayList<TerrainNode> sortedNodes = getSortedByDistanceNodes(map.getNodes(), node);
            for(TerrainNode sortedNode: sortedNodes){
                TerrainEdge edge = new TerrainEdge(node, sortedNode);
                if (isAllowed(edge)){
                    map.addEdge(edge);
                    node.addEdge(edge);
                    sortedNode.addEdge(edge);
                    count++;
                }
                if (count > minLinks - 1){
                    if (random.nextBoolean()) break;
                }
                if (count > maxLinks) break;
            }
        }
    }

    private static ArrayList<TerrainNode> getSortedByDistanceNodes(ArrayList<TerrainNode> nodes, TerrainNode center){
        ArrayList<TerrainNode> sortedNodes = new ArrayList<>();
        for(TerrainNode node: nodes) {
            if (node.equals(center)) continue;
            sortedNodes.add(node);
        }
        Index pos = center.getPos();

        Comparator<TerrainNode> sizeCmp = new Comparator<TerrainNode>() {

            @Override
            public int compare(TerrainNode n1, TerrainNode n2) {
                Index pos1 = n1.getPos();
                Index pos2 = n2.getPos();
                return (int) (pos1.distance(pos) - pos2.distance(pos));
            }
        };

        Collections.sort(sortedNodes, sizeCmp);
        return sortedNodes;
    }
}
