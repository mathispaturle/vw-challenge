package org.example.vwdh.application

import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.model.Workspace

class RobotController(private val workspace: Workspace) {

    fun deployRobots(robots: List<Robot>, instructions: List<String>) {
        if (robots.size != instructions.size) {
            throw IllegalArgumentException("Cada robot debe tener su serie de instrucciones")
        }

        robots.forEachIndexed { index, robot ->
            workspace.addRobot(robot)
            robot.executeInstructions(instructions[index])
        }
    }

    fun getFinalPositions(): List<String> {
        return workspace.getRobots().map { robot ->
            val pos = robot.position
            "${pos.x} ${pos.y} ${pos.orientation}"
        }
    }
}