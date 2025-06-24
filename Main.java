import java.util.*;

public class Main{

    public static void BFS(String start, String goal, Node[] nodeList){
        // This gonna be for bfs, input parameters still not final
        // Queue<Integer> q = new LinkedList<>();
    }

    public static void A_Star(String start, String goal, Node[] nodeList){
        // This gonna be for A*, input parameters still not final
        // Queue<Integer> q = new LinkedList<>();
    }

    public static void Search(Scanner sc, Node[] nodeList){
        System.out.print("Enter start node:");
        String start = sc.nextLine();
        System.out.print("Enter goal node:");
        String goal = sc.nextLine();
        System.out.println("1. BFS (Blind Search)\n2. A* (Heuristic Search)");
        int input = sc.nextInt();

        switch(input){
            case 1:
                BFS(start, goal, nodeList);
                break;
            case 2:
                A_Star(start, goal, nodeList);
                break;
            default:
                break;
        }
    }
    
    public static void main(String args[]){
        int[] arr = new int[50];

        for (int i = 0; i < 50; i++){
            arr[i] = i + 1;
        }
        
        Node[] nodeList = new Node[100];

        int nNode;// incase we need to add a new node

        for (nNode = 0; nNode < 21; nNode++){
            nodeList[nNode] = new Node(String.valueOf((char)('A' + nNode)));
        }

        System.out.println(nNode);
        boolean isok = nodeList[0].addEdge(nodeList[1]); // check if adding edge is successful
        
        System.out.println(isok);
        System.out.println(nodeList[0].getEdgeCount());

        //interface
        boolean running = true;
        
        Scanner sc = new Scanner(System.in);

        while(running){
            System.out.println("\nAvailable locations");

            for (int i = 0; i < nNode; i++){
                System.out.print(nodeList[i].getName());
                if(i != nNode - 1){
                    System.out.print(", ");
                }
            }

            System.out.println("\n1. Input start and goal\n2. Input new node\n3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    Search(sc, nodeList);
                    break;
                case 2:
                    System.out.print("Enter node name:");
                    String nName = sc.nextLine();
                    nodeList[nNode] = new Node(nName);
                    nNode++;
                    // edge part here
                    break;
                case 3:
                    System.out.print("Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        }

        sc.close();
    }
}