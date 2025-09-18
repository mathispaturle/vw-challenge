package vwdh.application


import org.example.vwdh.application.RobotController
import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.model.Workspace
import org.example.vwdh.domain.valueobject.Orientation
import org.example.vwdh.domain.valueobject.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RobotControllerTest {

    @Test
    fun exampleTestFromChallenge() {
        val workspace = Workspace(5, 5)
        val controller = RobotController(workspace)

        val robots = listOf(
            Robot(Position(1, 2, Orientation.N)),
            Robot(Position(3, 3, Orientation.E))
        )

        val instructions = listOf(
            "LMLMLMLMM",
            "MMRMMRMRRM"
        )

        controller.deployRobots(robots, instructions)
        val finalPositions = controller.getFinalPositions()

        assertEquals(listOf("1 3 N", "5 1 E"), finalPositions)
    }
}