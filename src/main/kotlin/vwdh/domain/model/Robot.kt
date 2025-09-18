package org.example.vwdh.domain.model

import org.example.vwdh.domain.valueobject.Position


class Robot(
    initialPosition: Position
) {
    var position: Position = initialPosition
        private set

    fun executeInstructions(instructions: String) {
        instructions.forEach { command ->
            val newPosition = when (command) {
                'L' -> position.copy(orientation = position.orientation.left())
                'R' -> position.copy(orientation = position.orientation.right())
                'M' -> position.move()
                else -> throw IllegalArgumentException("Comando desconocido: $command")
            }
            position = newPosition
        }
    }
}