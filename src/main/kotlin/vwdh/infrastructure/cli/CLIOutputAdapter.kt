package org.example.vwdh.infrastructure.cli

class CLIOutputAdapter {

    fun printFinalPositions(finalPositions: List<String>) {
        println("Posiciones finales de los robots:")
        finalPositions.forEach { println(it) }
    }
}