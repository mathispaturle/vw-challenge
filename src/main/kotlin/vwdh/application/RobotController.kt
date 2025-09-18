package org.example.vwdh.application

import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.model.Workspace

class RobotController(private val workspace: Workspace) {

    fun deployRobots(robots: List<Robot>, instructions: List<String>) {
        if (robots.size != instructions.size) {
            throw IllegalArgumentException("Each individual robot should have their separate set of instructions")
        }

        robots.forEachIndexed { index, robot ->
            workspace.addRobot(robot)
            robot.executeInstructions(instructions[index], workspace.maxX, workspace.maxY)
        }
    }

    fun getFinalPositions(): List<String> {
        return workspace.getRobots().map { robot ->
            val pos = robot.position
            "${pos.x} ${pos.y} ${pos.orientation}"
        }
    }
}