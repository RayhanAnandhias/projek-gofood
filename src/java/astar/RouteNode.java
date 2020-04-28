/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
import java.util.StringJoiner;
import model.Location;

class RouteNode implements Comparable<RouteNode> {
    private final Location current;
    private Location previous;
    private double routeScore;
    private double estimatedScore;

    RouteNode(Location current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    RouteNode(Location current, Location previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    Location getCurrent() {
        return current;
    }

    Location getPrevious() {
        return previous;
    }

    double getRouteScore() {
        return routeScore;
    }

    double getEstimatedScore() {
        return estimatedScore;
    }

    void setPrevious(Location previous) {
        this.previous = previous;
    }

    void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteNode other) {
        if (this.estimatedScore > other.estimatedScore) {
            return 1;
        } else if (this.estimatedScore < other.estimatedScore) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RouteNode.class.getSimpleName() + "[", "]").add("current=" + current)
            .add("previous=" + previous).add("routeScore=" + routeScore).add("estimatedScore=" + estimatedScore)
            .toString();
    }
}
