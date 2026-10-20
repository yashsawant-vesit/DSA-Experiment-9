
import java.util.Scanner;

public class Exp9 {
    static int[][] adj;
    static String[] cities;
    static boolean[] visited;
    static int n;

    // Breadth First Search using Queue
    static void BFS(int start) {
        visited = new boolean[n];
        int[] queue = new int[n];
        int front = 0, rear = 0;

        visited[start] = true;
        queue[rear++] = start;

        System.out.print("BFS Traversal: ");

        while (front < rear) {
            int current = queue[front++];
            System.out.print(cities[current] + " ");

            for (int i = 0; i < n; i++) {
                if (adj[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }
        System.out.println();
    }

    // Depth First Search using Recursion
    static void DFS(int current) {
        visited[current] = true;
        System.out.print(cities[current] + " ");

        for (int i = 0; i < n; i++) {
            if (adj[current][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Number of cities must be positive.");
            sc.close();
            return;
        }

        cities = new String[n];
        adj = new int[n][n];

        // Input city names
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of city " + (i + 1) + ": ");
            cities[i] = sc.nextLine();
        }

        // Input directed roads
        System.out.print("Enter number of one-way roads: ");
        int edges = sc.nextInt();

        if (edges < 0 || edges > n * n) {
            System.out.println("Invalid number of roads.");
            sc.close();
            return;
        }

        System.out.println("Enter each road using source and destination indices.");
        System.out.println("Example: 1 2 means city 1 -> city 2.");

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter road " + (i + 1) + ": ");
            int from = sc.nextInt();
            int to = sc.nextInt();

            if (from < 1 || from > n || to < 1 || to > n) {
                System.out.println("Invalid city indices. Try again.");
                i--;
                continue;
            }

            adj[from - 1][to - 1] = 1;
        }

        int choice;

        do {
            System.out.println("\n--- DIRECTED GRAPH MENU ---");
            System.out.println("1. Display Adjacency Matrix");
            System.out.println("2. Perform BFS");
            System.out.println("3. Perform DFS");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAdjacency Matrix:");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            System.out.print(adj[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.print("Enter starting city index (1-" + n + "): ");
                    int bfsStart = sc.nextInt();

                    if (bfsStart < 1 || bfsStart > n) {
                        System.out.println("Invalid starting city.");
                    } else {
                        BFS(bfsStart - 1);
                    }
                    break;

                case 3:
                    System.out.print("Enter starting city index (1-" + n + "): ");
                    int dfsStart = sc.nextInt();

                    if (dfsStart < 1 || dfsStart > n) {
                        System.out.println("Invalid starting city.");
                    } else {
                        visited = new boolean[n];
                        System.out.print("DFS Traversal: ");
                        DFS(dfsStart - 1);
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
