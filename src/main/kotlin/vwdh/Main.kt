package org.example.vwdh

import org.example.vwdh.application.RobotController
import org.example.vwdh.domain.model.Workspace
import org.example.vwdh.infrastructure.cli.CLIInputAdapter
import org.example.vwdh.infrastructure.cli.CLIOutputAdapter

fun main() {
    /*
     * Initialize input and output adapters.
     * These handle all interactions with the user via CLI.
     * InputAdapter reads workspace size and robot data.
     * OutputAdapter prints final robot positions.
     */
    val inputAdapter = CLIInputAdapter()
    val outputAdapter = CLIOutputAdapter()

    /*
     * Read the upper-right coordinates of the workspace from the user.
     * The workspace's bottom-left corner is implicitly (0,0).
     */
    val (maxX, maxY) = inputAdapter.readWorkspaceSize()
    val workspace = Workspace(maxX, maxY)

    /*
     * Initialize the RobotController, which handles robot deployment
     * and executes instructions sequentially within the workspace.
     */
    val controller = RobotController(workspace)

    /*
     * Read all robots' initial positions and their instruction strings.
     * Each robot is represented as a Pair<Robot, String>:
     * - first: Robot object with starting position and orientation
     * - second: instruction string (e.g., "LMLMMR")
     */
    val robotData = inputAdapter.readRobots()
    val robots = robotData.map { it.first }          // Extract Robot objects
    val instructions = robotData.map { it.second }  // Extract corresponding instructions

    /*
     * Deploy robots sequentially in the workspace and execute instructions.
     * The controller ensures moves respect workspace boundaries.
     */
    controller.deployRobots(robots, instructions)

    /*
     * Retrieve the final positions and orientations of all robots
     * after executing their instruction sets.
     */
    val finalPositions = controller.getFinalPositions()

    /*
     * Output the final positions to the console in the required format.
     * This keeps domain logic separated from input/output concerns.
     */
    outputAdapter.printFinalPositions(finalPositions)
}

