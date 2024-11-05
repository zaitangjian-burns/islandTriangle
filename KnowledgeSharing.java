import java.util.*;
//Algorithm ONE

// Dijkstra's algorithm to find shortest paths from a start island
public class KnowledgeSharing {

    private final Graph graph;

    private KnowledgeSharing(Graph graph) {
        this.graph = graph;
    }

    // Finds the shortest paths for a leader starting from a given island
    private Map<Island, Double> shortestPath(Island start) {
        Map<Island, Double> distances = new HashMap<>();
        PriorityQueue<IslandDistance> minHeap = new PriorityQueue<>(Comparator.comparingDouble(IslandDistance::getDistance));
        
        for (Island island : graph.getIslands()) {
            distances.put(island, Double.MAX_VALUE);
        }
        
        distances.put(start, 0.0);
        minHeap.add(new IslandDistance(start, 0.0));
        
        while (!minHeap.isEmpty()) {
            IslandDistance current = minHeap.poll();
            Island currentIsland = current.island;
            
            if (current.distance > distances.get(currentIsland)) continue;
            
            for (Route route : graph.getRoutesFrom(currentIsland)) {
                Island neighbor = route.getDestination();
                double newDist = distances.get(currentIsland) + route.getTravelTime();
                
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    minHeap.add(new IslandDistance(neighbor, newDist));
                }
            }
        }
        
        return distances;
    }

    // Prioritize islands by population and find the shortest paths in that order
    private void distributeKnowledge(Island start) {
        List<Island> islands = new ArrayList<>(graph.getIslands());
        
        // Sort islands by population in descending order
        islands.sort((i1, i2) -> Integer.compare(i2.population, i1.population));
        
        // Get shortest paths for each prioritized island
        System.out.println("Distributing knowledge from " + start.name + " by population priority:");
        for (Island island : islands) {
            Map<Island, Double> distances = shortestPath(start);
            Double distanceToIsland = distances.get(island);
            System.out.println("Travel time to " + island.name + " (Population: " + island.population + ") from " + start.name + ": " + distanceToIsland);
        }
    }


    // (Helper class) stores island and its distance for the priority queue
    private static class IslandDistance {
        Island island;
        double distance;

        public IslandDistance(Island island, double distance) {
            this.island = island;
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }
    }

    // Demonstrate functionality
    public static void main(String[] args) {
        // Creating islands
        Island islandA = new Island("A", 1000);
        Island islandB = new Island("B", 800);
        Island islandC = new Island("C", 600);
        Island islandD = new Island("D", 400);
        islandA.addResource("Water", 100);
        islandB.addResource("Food", 50);
        islandC.addExperience("Fishing", 30);
        islandD.addExperience("Crafting", 20);
        Graph graph = new Graph();
        graph.addIsland(islandA);
        graph.addIsland(islandB);
        graph.addIsland(islandC);
        graph.addIsland(islandD);
        graph.addRoute(islandA, islandB, 1.5);
        graph.addRoute(islandA, islandC, 2.0);
        graph.addRoute(islandB, islandD, 2.5);
        graph.addRoute(islandC, islandD, 1.0);

        // Creating KnowledgeSharing instance and demonstrating knowledge distribution
        KnowledgeSharing knowledgeSharing = new KnowledgeSharing(graph);
        knowledgeSharing.distributeKnowledge(islandA);  // Demonstrates population-prioritized pathfinding
    }
}
