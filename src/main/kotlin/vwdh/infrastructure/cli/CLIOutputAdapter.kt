package org.example.vwdh.infrastructure.cli

class CLIOutputAdapter {

    fun printFinalPositions(finalPositions: List<String>) {
        println("Final robot positions:")
        finalPositions.forEach { println(it) }
    }
}