// For algorithm 3 (breadth-first search)
public class Main {
    public static void main(String[] args) {
        // 4 different islands with different populations
        Island A = new Island("A", 200);  //Origin of primary source
        Island B = new Island("B", 150);
        Island C = new Island("C", 100);  //smallest population
        Island D = new Island("D", 250);  //biggest population

        // Adding resourcing to origin of primary source
        A.addResource("Special Resource", 300);  

        // Setting up the graph (sea of islands)
        Graph graph = new Graph();
        graph.addIsland(A);
        graph.addIsland(B);
        graph.addIsland(C);
        graph.addIsland(D);

        // Adding routes with travel times between islands
        graph.addRoute(A, B, 2.0);  // Travel time from A to B
        graph.addRoute(A, C, 3.0);  // Travel time from A to C
        graph.addRoute(B, D, 1.5);  // Travel time from B to D
        graph.addRoute(C, D, 2.5);  // Travel time from C to D

        // Initializing resource distributor and running distribution
        ResourceDistributor distributor = new ResourceDistributor(graph);
        distributor.distributeResource("Special Resource", A);

        // Displaying the final resource counts on each island
        for (Island island : graph.getIslands()) {
            System.out.println("Island: " + island.name + ", Resources: " + island.resources);
        }
    }
}
