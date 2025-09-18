package org.example.vwdh

import org.example.vwdh.application.RobotController
import org.example.vwdh.domain.model.Workspace
import org.example.vwdh.infrastructure.cli.CLIInputAdapter
import org.example.vwdh.infrastructure.cli.CLIOutputAdapter

fun main() {
    val inputAdapter = CLIInputAdapter()
    val outputAdapter = CLIOutputAdapter()

    val (maxX, maxY) = inputAdapter.readWorkspaceSize()
    val workspace = Workspace(maxX, maxY)
    val controller = RobotController(workspace)

    val robotData = inputAdapter.readRobots()
    val robots = robotData.map { it.first }
    val instructions = robotData.map { it.second }

    controller.deployRobots(robots, instructions)
    val finalPositions = controller.getFinalPositions()

    outputAdapter.printFinalPositions(finalPositions)
}
