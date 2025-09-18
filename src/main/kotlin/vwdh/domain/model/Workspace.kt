package org.example.vwdh.domain.model

import org.example.vwdh.domain.valueobject.Position

class Workspace(
    val maxX: Int,
    val maxY: Int
) {
    private val robots = mutableListOf<Robot>()

    fun addRobot(robot: Robot) {
        validatePosition(robot.position)
        robots.add(robot)
    }

    fun getRobots(): List<Robot> = robots

    private fun validatePosition(position: Position) {
        if (position.x !in 0..maxX || position.y !in 0..maxY) {
            throw IllegalArgumentException(
                "Position is out of bounds: $position"
            )
        }
    }
}