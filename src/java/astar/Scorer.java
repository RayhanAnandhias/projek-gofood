/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}
