import java.util.ArrayList;

public class Node{
    private String name;
    private ArrayList<Node> edgeNodes;
    private ArrayList<Integer> edgeCosts; // for edgecosts and heuristics, they match indexes with the edgenodes
    private ArrayList<Integer> heuristics;
    public boolean isVisited;

    public Node(String name){
        this.name = name;
        this.edgeNodes = new ArrayList<Node>();
        this.edgeCosts = new ArrayList<Integer>();
        this.heuristics= new ArrayList<Integer>();
    }

    public String getName(){
        return this.name;
    }

    public int getEdgeCount(){
        return this.edgeNodes.size();
    }

    public ArrayList<Node> getEdgeNodes(){
        return this.edgeNodes;
    }

    public boolean addEdge(Node newEdge){
        try{
            this.edgeNodes.add(newEdge);
            return true;
        }
        catch(Exception E){
            System.out.println(E);
            return false;
        }
    }

    public int getHeuristicOfEdge(String edgeName){
        for (int i = 0; i < this.getEdgeCount(); i++){
            if (this.edgeNodes.get(i).getName() == edgeName){
                return this.heuristics.get(i);
            }
        }

        return -1;
    }

}