package org.example.vwdh.domain.valueobject

enum class Orientation {
    N, E, S, W;

    fun left(): Orientation = when (this) {
        N -> W
        W -> S
        S -> E
        E -> N
    }

    fun right(): Orientation = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }
}