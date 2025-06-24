import java.util.*;

public class Main{

    public static void BFS(String start, String goal, Node[] nodeList){
        Node startNode = null;
        Node goalNode = null;

        
        for (Node node : nodeList){
            if (node != null){
                node.isVisited = false;

                if (node.getName().equals(start)) {
                    startNode = node;
                }
                if (node.getName().equals(goal)) {
                    goalNode = node;
                }
            }
        }

        if (startNode == null || goalNode == null){
            System.out.println("Start or goal node not found.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        HashMap<String, String> parentMap = new HashMap<>(); 

        queue.add(startNode);
        startNode.isVisited = true;

        boolean found = false;

        while (!queue.isEmpty()){
            Node current = queue.poll();
            System.out.println("Visiting: " + current.getName());

            if (current == goalNode){
                found = true;
                break;
            }

            for (Node neighbor : current.getEdgeNodes()){
                if (!neighbor.isVisited){
                    neighbor.isVisited = true;
                    queue.add(neighbor);
                    parentMap.put(neighbor.getName(), current.getName());
                }
            }
        }

        if (found){
            LinkedList<String> path = new LinkedList<>();
            String step = goal;
            while (step != null){
                path.addFirst(step);
                step = parentMap.get(step);
            }

            System.out.println("BFS Path: " + String.join(" -> ", path));
        } else {
            System.out.println("No path found.");
        }
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

        for (nNode = 0; nNode < 22; nNode++){
            nodeList[nNode] = new Node(String.valueOf((char)('A' + nNode)));
        }

        System.out.println(nNode);

        // A = 0
        nodeList[0].addEdge(nodeList[1], 1, 1);   // A-B
        nodeList[1].addEdge(nodeList[0], 1, 1);   // B-A
        nodeList[0].addEdge(nodeList[20], 3, 3);  // A-T
        nodeList[20].addEdge(nodeList[0], 3, 3);  // T-A

        // B = 1
        nodeList[1].addEdge(nodeList[2], 2, 2);   // B-C
        nodeList[2].addEdge(nodeList[1], 2, 2);   // C-B
        nodeList[1].addEdge(nodeList[20], 3, 3);  // B-T
        nodeList[20].addEdge(nodeList[1], 3, 3);  // T-B

        // C = 2
        nodeList[2].addEdge(nodeList[19], 3, 3);  // C-R
        nodeList[19].addEdge(nodeList[2], 3, 3);  // R-C

        // D = 3
        nodeList[3].addEdge(nodeList[4], 1, 1);   // D-E
        nodeList[4].addEdge(nodeList[3], 1, 1);   // E-D
        nodeList[3].addEdge(nodeList[2], 8, 8);   // D-C
        nodeList[2].addEdge(nodeList[3], 8, 8);   // C-D

        // E = 4
        nodeList[4].addEdge(nodeList[5], 3, 3);   // E-F
        nodeList[5].addEdge(nodeList[4], 3, 3);   // F-E
        nodeList[4].addEdge(nodeList[14], 3, 3); // E-O
        nodeList[14].addEdge(nodeList[4], 3, 3); // O-E

        // F = 5
        nodeList[5].addEdge(nodeList[6], 3, 3);   // F-G
        nodeList[6].addEdge(nodeList[5], 3, 3);   // G-F
        nodeList[5].addEdge(nodeList[14], 3, 3);  // F-O
        nodeList[14].addEdge(nodeList[5], 3, 3);  // O-F

        // G = 6
        nodeList[6].addEdge(nodeList[8], 2, 2);   // G-I
        nodeList[8].addEdge(nodeList[6], 2, 2);   // I-G
        nodeList[6].addEdge(nodeList[7], 4, 4);   // G-H
        nodeList[7].addEdge(nodeList[6], 4, 4);   // H-G

        // I = 8
        nodeList[8].addEdge(nodeList[9], 1, 1);   // I-J
        nodeList[9].addEdge(nodeList[8], 1, 1);   // J-I

        // J = 9
        nodeList[9].addEdge(nodeList[10], 1, 1);  // J-K
        nodeList[10].addEdge(nodeList[9], 1, 1);  // K-J
        nodeList[9].addEdge(nodeList[12], 4, 4);  // J-M
        nodeList[12].addEdge(nodeList[9], 4, 4);  // M-J
        nodeList[9].addEdge(nodeList[21], 4, 4);  // J-V
        nodeList[21].addEdge(nodeList[9], 4, 4);  // V-J
        nodeList[9].addEdge(nodeList[13], 4, 4); // J-N
        nodeList[13].addEdge(nodeList[9], 4, 4); // N-J

        // K = 10
        nodeList[10].addEdge(nodeList[16], 1, 1); // K-U
        nodeList[16].addEdge(nodeList[10], 1, 1); // U-K
        nodeList[10].addEdge(nodeList[12], 2, 2); // K-M
        nodeList[12].addEdge(nodeList[10], 2, 2); // M-K

        // L = 11
        nodeList[11].addEdge(nodeList[21], 1, 1); // L-V
        nodeList[21].addEdge(nodeList[11], 1, 1); // V-L
        nodeList[7].addEdge(nodeList[11], 4, 4); // H-L
        nodeList[11].addEdge(nodeList[7], 4, 4); // L-H
        nodeList[11].addEdge(nodeList[8], 2, 2); // L-I
        nodeList[8].addEdge(nodeList[11], 2, 2); // I-L

        // M = 12
        nodeList[12].addEdge(nodeList[13], 1, 1); // M-N
        nodeList[13].addEdge(nodeList[12], 1, 1); // N-M

        // N = 13
        nodeList[13].addEdge(nodeList[14], 6, 6); // N-O
        nodeList[14].addEdge(nodeList[13], 6, 6); // O-N
        nodeList[13].addEdge(nodeList[8], 3, 3);  // N-I
        nodeList[8].addEdge(nodeList[13], 3, 3);  // I-N

        // O = 14
        nodeList[14].addEdge(nodeList[15], 4, 4); // O-P
        nodeList[15].addEdge(nodeList[14], 4, 4); // P-O
        nodeList[14].addEdge(nodeList[17], 5, 5); // O-S
        nodeList[17].addEdge(nodeList[14], 5, 5); // S-O

        // P = 15
        nodeList[15].addEdge(nodeList[18], 1, 1); // P-Q
        nodeList[18].addEdge(nodeList[15], 1, 1); // Q-P
        nodeList[15].addEdge(nodeList[17], 1, 1); // P-S
        nodeList[17].addEdge(nodeList[15], 1, 1); // S-P

        // Q = 18
        nodeList[18].addEdge(nodeList[19], 2, 2); // Q-R
        nodeList[19].addEdge(nodeList[18], 2, 2); // R-Q
        nodeList[18].addEdge(nodeList[17], 2, 2); // Q-S
        nodeList[17].addEdge(nodeList[18], 2, 2); // S-Q

        // R = 19
        nodeList[19].addEdge(nodeList[20], 4, 4); // R-T
        nodeList[20].addEdge(nodeList[19], 4, 4); // T-R

        // T = 20
        // already fully connected above

        // U = 16
        nodeList[16].addEdge(nodeList[21], 3, 3); // U-V
        nodeList[21].addEdge(nodeList[16], 3, 3); // V-U

        // V = 21
        // already connected via J and L and U
        

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