package org.example.vwdh.domain.valueobject

data class Position(
    val x: Int,
    val y: Int,
    val orientation: Orientation
) {

    fun move(): Position = when (orientation) {
        Orientation.N -> copy(y = y + 1)
        Orientation.E -> copy(x = x + 1)
        Orientation.S -> copy(y = y - 1)
        Orientation.W -> copy(x = x - 1)
    }
}