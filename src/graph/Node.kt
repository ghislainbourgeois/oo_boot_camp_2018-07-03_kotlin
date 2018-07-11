/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands its neighbors
class Node {
    private val unreachable = Double.POSITIVE_INFINITY
    private val neighbors = mutableListOf<Node>()

    fun to(neighbor: Node): Node {
        neighbors.add(neighbor)
        return neighbor
    }

    fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != unreachable

    fun hopCount(destination: Node): Int {
        return hopCount(destination, noVisitedNodes).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}.toInt()
    }

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (visitedNodes.contains(this)) return unreachable
        return neighborHopCount(destination, visitedNodes)
    }

    private fun neighborHopCount(destination: Node, visitedNodes: List<Node>): Double {
        var champion = unreachable
        for (n in neighbors) {
            val challenger = n.hopCount(destination, visitedNodes + this) + 1
            if (challenger < champion) champion = challenger
        }
        return champion
    }

    private val noVisitedNodes get() = listOf<Node>()
}