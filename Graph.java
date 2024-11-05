import java.util.*;

// Island class representing each island
class Island {
    String name;
    int population;
    Map<String, Integer> resources;
    Map<String, Integer> experiences;

    public Island(String name, int population) {
        this.name = name;
        this.population = population;
        this.resources = new HashMap<>();
        this.experiences = new HashMap<>();
    }

    public void addResource(String resource, int quantity) {
        resources.put(resource, quantity);
    }

    public void addExperience(String experience, int time) {
        experiences.put(experience, time);
    }
}

// Route class representing a directed edge between islands
class Route {
    Island destination;
    double travelTime;

    public Route(Island destination, double travelTime) {
        this.destination = destination;
        this.travelTime = travelTime;
    }

    public Island getDestination() {
        return destination;
    }

    public double getTravelTime() {
        return travelTime;
    }
}

// Graph class representing the network of islands and routes
class Graph {
    Map<Island, List<Route>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addIsland(Island island) {
        adjacencyList.putIfAbsent(island, new ArrayList<>());
    }

    public void addRoute(Island from, Island to, double travelTime) {
        adjacencyList.get(from).add(new Route(to, travelTime));
    }

    public List<Route> getRoutesFrom(Island island) {
        return adjacencyList.getOrDefault(island, new ArrayList<>());
    }

    public Set<Island> getIslands() {
        return adjacencyList.keySet();
    }
}

