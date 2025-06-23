import java.util.*;

public class Main{

    public static void BFS(){
        // This gonna be for bfs, input parameters still not final
        // Queue<Integer> q = new LinkedList<>();
    }
    
    public static void main(String args[]){
        int[] arr = new int[50];

        for (int i = 0; i < 50; i++){
            arr[i] = i + 1;
        }
        
        Node[] nodeList = new Node[21];
        
        for (int i = 0; i < 21; i++){
            nodeList[i] = new Node((char)('A' + i));
        }

        boolean isok = nodeList[0].addEdge(nodeList[1]); // check if adding edge is successful
        
        System.out.println(isok);
        System.out.println(nodeList[0].getEdgeCount());
    }
}