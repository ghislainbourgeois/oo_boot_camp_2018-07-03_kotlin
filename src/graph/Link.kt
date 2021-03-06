/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands a connection to a specific Node
class Link(private val cost: Double, private val target: Node) {

    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        return target.hopCount(destination, visitedNodes) + 1
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>): Double {
        return target.cost(destination, visitedNodes) + cost
    }
}