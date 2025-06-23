import java.util.ArrayList;

public class Node{
    private char name;
    private ArrayList<Node> edgeNodes;
    public boolean isVisited;

    public Node(char name){
        this.name = name;
        this.edgeNodes = new ArrayList<Node>();
    }

    public char getName(){
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

}