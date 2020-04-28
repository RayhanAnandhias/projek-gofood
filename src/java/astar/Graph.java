/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import model.Location;


public class Graph {
    private final Set<Location> nodes;

    private final Map<String, Set<String>> connections;

    public Graph(Set<Location> nodes, Map<String, Set<String>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public Location getNode(String id) {
        return nodes.stream()
            .filter(node -> node.getKode().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No node found with ID"));
    }

    public Set<Location> getConnections(Location node) {
        return connections.get(node.getKode()).stream()
            .map(this::getNode)
            .collect(Collectors.toSet());
    }
}
