import java.util.*;

public class Main{

    public static void BFS(){
        // This gonna be for bfs, input parameters still not final
        // Queue<Integer> q = new LinkedList<>();
    }

    public static void A_Star(){
        // This gonna be for A*, input parameters still not final
        // Queue<Integer> q = new LinkedList<>();
    }

    public static void Search(){

    }
    
    public static void main(String args[]){
        int[] arr = new int[50];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 50; i++){
            arr[i] = i + 1;
        }
        
        Node[] nodeList = new Node[21];
        int nNode;// incase we need to add a new node
        for (nNode = 0; nNode < 21; nNode++){
            nodeList[nNode] = new Node((String)('A' + nNode));
        }
        System.out.println(nNode);
        boolean isok = nodeList[0].addEdge(nodeList[1]); // check if adding edge is successful
        
        System.out.println(isok);
        System.out.println(nodeList[0].getEdgeCount());

        //interface
        boolean running = true;
        while(running){
            System.out.println("Available locations");
            for (int i = 0; i < nNode; i++){
                System.out.print(nodeList[i].getName()+", ");
            }
            System.out.println("1. Input start and goal\n2. Input new node\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case '1':
                    Search();
                    break;
                case '2':
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        sc.close();
    }
}