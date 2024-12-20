import java.util.Scanner;

public class BellmanFord 
{
    private int[] D;
    private static final int max = 999;

    public BellmanFord(int vertices) 
    {
        D = new int[vertices + 1];
    }

    public void evaluate(int source, int[][] graph, int vertices) 
    {
        for (int i = 1; i <= vertices; i++) 
        {
            D[i] = max;
        }
        D[source] = 0;

        for (int i = 1; i < vertices; i++) 
        {
            for (int u = 1; u <= vertices; u++) 
            {
                for (int v = 1; v <= vertices; v++) 
                {
                    if (graph[u][v] != max && D[u] + graph[u][v] < D[v]) 
                    {
                        D[v] = D[u] + graph[u][v];
                    }
                }
            }
        }

        for (int u = 1; u <= vertices; u++) 
        {
            for (int v = 1; v <= vertices; v++) 
            {
                if (graph[u][v] != max && D[u] + graph[u][v] < D[v]) 
                {
                    System.out.println("Graph contains a negative weight cycle.");
                    return;
                }
            }
        }

        for (int i = 1; i <= vertices; i++) 
        {
            System.out.println("Distance from source " + source + " to " + i + " is " + D[i]);
        }
    }

    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = s.nextInt();

        int[][] graph = new int[vertices + 1][vertices + 1];
        System.out.println("Enter adjacency matrix (use 0 for no edge):");
        for (int i = 1; i <= vertices; i++) 
        {
            for (int j = 1; j <= vertices; j++) 
            {
                graph[i][j] = s.nextInt();
                if (i == j) 
                {
                    graph[i][j] = 0;
                } 
                else if (graph[i][j] == 0) 
                {
                    graph[i][j] = max;
                }
            }
        }

        System.out.print("Enter source vertex: ");
        int source = s.nextInt();

        new BellmanFord(vertices).evaluate(source, graph, vertices);
        s.close();
    }
}
