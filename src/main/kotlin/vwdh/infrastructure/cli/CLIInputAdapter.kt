package org.example.vwdh.infrastructure.cli

import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.valueobject.Orientation
import org.example.vwdh.domain.valueobject.Position

class CLIInputAdapter {

    fun readWorkspaceSize(): Pair<Int, Int> {
        println("Introduce las coordenadas superiores derechas del workspace (ej: 5 5):")
        val line = readln().trim()
        val parts = line.split(" ")
        if (parts.size != 2) throw IllegalArgumentException("Formato incorrecto")
        val maxX = parts[0].toInt()
        val maxY = parts[1].toInt()
        return maxX to maxY
    }

    fun readRobots(): List<Pair<Robot, String>> {
        val robots = mutableListOf<Pair<Robot, String>>()
        println("Introduce datos de los robots (posición y comandos). Finaliza con línea vacía:")
        while (true) {
            val positionLine = readlnOrNull()?.trim() ?: break
            if (positionLine.isEmpty()) break
            val parts = positionLine.split(" ")
            if (parts.size != 3) throw IllegalArgumentException("Formato de posición incorrecto")
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])
            val robot = Robot(Position(x, y, orientation))

            val instructionLine = readlnOrNull()?.trim() ?: throw IllegalArgumentException("Faltan instrucciones")
            robots.add(robot to instructionLine)
        }
        return robots
    }
}