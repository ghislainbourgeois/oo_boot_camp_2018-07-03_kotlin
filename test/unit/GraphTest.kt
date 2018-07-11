/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import graph.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

// Ensure algorithms on directed graphs operate correctly
class GraphTest {
    private val a = Node()
    private val b = Node()
    private val c = Node()
    private val d = Node()
    private val e = Node()
    private val f = Node()
    private val g = Node()

    init {
        b.to(a)
        b.to(c).to(d).to(e).to(b).to(f)
        c.to(d)
        c.to(e)
    }

    @Test fun `can reach`() {
        assert(b.canReach(b))
        assert(b.canReach(a))
        assert(b.canReach(f))
        assert(b.canReach(d))
        assert(c.canReach(f))
        assertFalse(g.canReach(b))
        assertFalse(a.canReach(b))
        assertFalse(b.canReach(g))
    }

    @Test fun `hop count`() {
        assertEquals(0, b.hopCount(b))
        assertEquals(1, b.hopCount(a))
        assertEquals(1, b.hopCount(f))
        assertEquals(2, b.hopCount(d))
        assertEquals(3, c.hopCount(f))
        assertFailsWith(IllegalArgumentException::class) { g.hopCount(b) }
        assertFailsWith(IllegalArgumentException::class) { a.hopCount(b) }
        assertFailsWith(IllegalArgumentException::class) { b.hopCount(g) }
    }
}