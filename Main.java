import java.util.*;

public class Main {

    public static class HeuristicTable {
        public static final String[] NAMES = {"A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V"};
        private static final String[] CSV_ROWS = {
            "0,1,3,11,11,12,15,15,17,18,19,18,19,18,11,8,7,5,8,3,23,25",
            "1,0,2,10,13,14,17,21,19,20,21,23,23,22,12,8,7,5,9,3,22,24",
            "3,2,0,8,11,12,15,19,17,18,19,21,21,20,10,6,5,3,7,5,20,22",
            "11,10,8,0,3,3,5,4,9,10,11,13,13,12,6,10,11,11,11,13,12,14",
            "11,13,11,3,0,1,4,4,6,7,8,10,10,9,3,5,5,7,5,11,9,8",
            "12,14,12,3,1,0,3,5,5,6,7,7,7,5,3,5,6,8,6,12,8,10",
            "15,17,15,5,4,3,0,4,2,3,4,6,6,5,5,8,9,12,8,15,5,5",
            "15,21,19,4,4,5,4,0,4,5,5,2,8,7,8,10,11,13,10,16,5,3",
            "17,19,17,9,6,5,2,4,0,1,2,4,4,3,6,10,11,13,10,17,3,4",
            "18,20,18,10,7,6,3,5,1,0,1,4,4,3,6,10,11,13,10,17,2,4",
            "19,21,19,11,8,7,4,5,2,1,0,4,3,3,7,11,12,14,11,17,1,3",
            "18,23,21,13,10,7,6,2,4,4,4,0,7,7,9,12,13,14,12,18,3,1",
            "19,23,21,13,10,7,6,8,4,4,3,7,0,1,7,11,12,14,14,18,4,6",
            "18,22,20,12,6,5,5,7,3,3,3,7,1,0,6,10,11,13,13,17,4,6",
            "11,12,10,6,3,3,5,8,6,6,7,9,7,6,0,4,5,7,5,11,8,9",
            "9,8,6,10,7,7,10,14,12,13,14,17,11,10,4,0,1,3,1,7,11,12",
            "7,7,5,11,5,6,9,11,11,11,12,13,12,11,5,1,0,2,2,6,12,13",
            "6,5,3,11,7,8,12,13,13,13,14,14,14,13,7,3,2,0,3,4,14,15",
            "10,9,7,11,9,9,11,15,13,14,15,18,12,11,5,1,2,4,0,7,15,13",
            "3,3,5,13,11,12,15,16,17,17,17,18,18,17,11,7,6,4,7,0,18,19",
            "23,22,20,12,9,8,5,5,3,2,1,3,4,4,8,11,12,14,15,18,0,2",
            "25,24,22,14,8,10,5,3,4,4,3,1,6,6,9,12,13,15,13,19,2,0"
        };
        private static final Map<String,Map<String,Integer>> MAP = buildMap();
        private static Map<String,Map<String,Integer>> buildMap() {
            Map<String,Map<String,Integer>> m = new HashMap<>();
            for (int i = 0; i < NAMES.length; i++) {
                String[] parts = CSV_ROWS[i].split(",");
                Map<String,Integer> row = new HashMap<>();
                for (int j = 0; j < parts.length; j++) row.put(NAMES[j], Integer.parseInt(parts[j]));
                m.put(NAMES[i], row);
            }
            return m;
        }
        public static int h(String from, String to) {
            return MAP.getOrDefault(from, Collections.emptyMap()).getOrDefault(to, Integer.MAX_VALUE);
        }
    }

    public static class EdgeCostTable {
        private static final Map<String, Map<String, Integer>> MAP = new HashMap<>();
        static {
            String[] data = {
                "A,B,1","A,T,3","B,C,2","B,T,3","C,D,8","C,R,3",
                "D,E,3","E,F,1","E,O,3","F,G,3","F,O,3","G,H,4",
                "G,I,2","H,L,2","I,J,1","I,L,4","I,N,3","J,K,1",
                "J,M,4","J,N,3","J,V,4","K,U,1","L,V,1","M,N,1",
                "N,O,6","O,P,4","O,S,5","P,Q,1","P,S,1",
                "Q,R,2","Q,S,2", "U,V,2"
            };
            for (String entry : data) {
                String[] parts = entry.split(",");
                String a = parts[0], b = parts[1];
                int cost = Integer.parseInt(parts[2]);
                MAP.computeIfAbsent(a, k -> new HashMap<>()).put(b, cost);
                MAP.computeIfAbsent(b, k -> new HashMap<>()).put(a, cost);
            }
        }
        public static int cost(String from, String to) {
            return MAP.getOrDefault(from, Collections.emptyMap()).getOrDefault(to, Integer.MAX_VALUE);
        }
        public static boolean isConnected(String from, String to) {
            return MAP.containsKey(from) && MAP.get(from).containsKey(to);
        }
        public static Map<String, Integer> getedgeNodes(String node) {
            return MAP.getOrDefault(node, Collections.emptyMap());
        }
    }

    public static void A_Star(String startName, String goalName, Node[] nodeList) {
        Node start = null, goal = null;
        for (Node n : nodeList) if (n!=null) {
            if (n.getName().equals(startName)) start=n;
            if (n.getName().equals(goalName))  goal =n;
        }
        if (start==null||goal==null) { System.out.println("Start/goal not found."); return; }

        Map<Node,Integer> gMap = new HashMap<>();
        Map<Node,Node> parent = new HashMap<>();
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> gMap.getOrDefault(n, Integer.MAX_VALUE) + HeuristicTable.h(n.getName(), goalName)));

        gMap.put(start, 0);
        open.add(start);

        while (!open.isEmpty()) {
            Node cur = open.poll();
            if (cur == goal) break;
            int curG = gMap.get(cur);
            for (int i = 0; i < cur.getEdgeNodes().size(); i++) {
                Node nb = cur.getEdgeNodes().get(i);
                int cost = cur.getEdgeCosts().get(i);
                int tg = curG + cost;
                if (tg < gMap.getOrDefault(nb, Integer.MAX_VALUE)) {
                    gMap.put(nb, tg);
                    parent.put(nb, cur);
                    open.remove(nb);
                    open.add(nb);
                }
            }
        }

        List<Node> path = new ArrayList<>();
        for (Node at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        int totalG = gMap.getOrDefault(goal, Integer.MAX_VALUE);

        System.out.print("Optimal Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName());
            if (i < path.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\nTotal path cost: " + totalG);
    }


    public static void Search(Scanner sc, Node[] nodeList) {
        System.out.println("\nAvailable locations: " + String.join(", ", HeuristicTable.NAMES));
        System.out.print("1. A* Search\n2. Exit\nChoose: ");
        int choice = sc.nextInt(); sc.nextLine();
        if (choice == 1) {
            System.out.print("Enter start: "); String s = sc.nextLine().trim();
            System.out.print("Enter goal:  "); String g = sc.nextLine().trim();
            A_Star(s, g, nodeList);
        } else if (choice == 2) {
            System.out.println("Exiting.");
            System.exit(0);
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node[] nodes = new Node[HeuristicTable.NAMES.length];
        for (int i = 0; i < nodes.length; i++) nodes[i] = new Node(HeuristicTable.NAMES[i]);
        for (String from : HeuristicTable.NAMES) {
            for (String to : HeuristicTable.NAMES) {
                if (from.equals(to)) continue;
                int cost = EdgeCostTable.cost(from, to);
                if (cost != Integer.MAX_VALUE) {
                    Node fNode = nodes[Arrays.asList(HeuristicTable.NAMES).indexOf(from)];
                    Node tNode = nodes[Arrays.asList(HeuristicTable.NAMES).indexOf(to)];
                    fNode.addEdge(tNode, cost);
                }
            }
        }
        // Menu loop
        while (true) {
            Search(sc, nodes);
        }
    }
}