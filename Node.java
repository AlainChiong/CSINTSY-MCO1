import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> edgeNodes;
    private ArrayList<Integer> edgeCosts;
    public boolean isVisited;

    public Node(String name) {
        this.name = name;
        this.edgeNodes = new ArrayList<Node>();
        this.edgeCosts = new ArrayList<Integer>();
        this.isVisited = false;
    }

    public String getName() {
        return this.name;
    }

    public int getEdgeCount() {
        return this.edgeNodes.size();
    }

    public ArrayList<Node> getEdgeNodes() {
        return this.edgeNodes;
    }

    public boolean addEdge(Node newEdge, int edgeCost){
        try{
            this.edgeNodes.add(newEdge);
            this.edgeCosts.add(edgeCost);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<Integer> getEdgeCosts() {
        return this.edgeCosts;
    }


    public int getEdgeCostTo(Node target) {
        for (int i = 0; i < edgeNodes.size(); i++) {
            if (edgeNodes.get(i).getName().equals(target.getName())) {
                return edgeCosts.get(i);
            }
        }
        return Integer.MAX_VALUE;
    }
}
