package org.example.vwdh.domain.valueobject

data class Position(
    val x: Int,
    val y: Int,
    val orientation: Orientation
) {

    fun move(maxX: Int, maxY: Int): Position {
        val (newX, newY) = when (orientation) {
            Orientation.N -> x to y + 1
            Orientation.E -> x + 1 to y
            Orientation.S -> x to y - 1
            Orientation.W -> x - 1 to y
        }

        if (newX !in 0..maxX || newY !in 0..maxY) {
            throw IllegalStateException("Cannot move outside workspace boundaries")
        }

        return copy(x = newX, y = newY)
    }
}