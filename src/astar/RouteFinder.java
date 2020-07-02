/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import model.Location;

public class RouteFinder {
    
    private final Graph graph;
    private final HaversineScorer nextNodeScorer;
    private final HaversineScorer targetScorer;

    public RouteFinder(Graph graph, HaversineScorer nextNodeScorer, HaversineScorer targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<Location> findRoute(Location from, Location to) {
        Map<Location, RouteNode> allNodes = new HashMap<>();
        Queue<RouteNode> openSet = new PriorityQueue<>();

        RouteNode start = new RouteNode(from, null, 0d, targetScorer.computeCost(from, to));
        allNodes.put(from, start);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            System.out.println("Open Set contains: " + openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            RouteNode next = openSet.poll();
            System.out.println("Looking at node: " + next);
            if (next.getCurrent().getKode().equals(to.getKode())) {
                System.out.println("Found our destination!");

                List<Location> route = new ArrayList<>();
                RouteNode current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

                System.out.println("Route: " + route);
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                double newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
                RouteNode nextNode = allNodes.getOrDefault(connection, new RouteNode(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
                    System.out.println("Found a better route to node: " + nextNode);
                }
            });
        }

        throw new IllegalStateException("No route found");
    }

}