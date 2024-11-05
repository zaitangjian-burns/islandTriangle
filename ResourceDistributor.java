import java.util.*;
// For algorithm 3 (breadth-first search)
public class ResourceDistributor {
    private Graph graph;

    public ResourceDistributor(Graph graph) {
        this.graph = graph;
    }

    public void distributeResource(String resourceName, Island originIsland) {
        Queue<Island> queue = new LinkedList<>();
        Set<Island> visited = new HashSet<>();
        
        // Initialize by putting the origin island in the queue
        queue.add(originIsland);
        visited.add(originIsland);

        while (!queue.isEmpty()) {
            Island currentIsland = queue.poll();

            // Calculate the distribution amount for the current island
            int currentResource = currentIsland.resources.getOrDefault(resourceName, 0);

            // Iterate over neighbors of the current island
            for (Route route : graph.getRoutesFrom(currentIsland)) {
                Island neighbor = route.getDestination();

                // Skip if island is already visited
                if (!visited.contains(neighbor)) {
                    
                    int population = neighbor.population;

                    // Ensure population is not zero to avoid division by zero
                    if (population > 0) {
                        int resourceToDistribute = currentResource / 2; // Example amount
                        neighbor.addResource(resourceName, resourceToDistribute);

                        
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
    }
}
