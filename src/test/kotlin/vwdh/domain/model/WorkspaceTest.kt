package vwdh.domain.model

import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.model.Workspace
import org.example.vwdh.domain.valueobject.Orientation
import org.example.vwdh.domain.valueobject.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class WorkspaceTest {

    @Test
    fun addRobotInsideBoundsCorrectTest() {
        val workspace = Workspace(5, 5)
        val robot = Robot(Position(1, 2, Orientation.N))
        workspace.addRobot(robot)
        assertEquals(1, workspace.getRobots().size)
    }

    @Test
    fun addRobotOutOfBoundsExceptionTest() {
        val workspace = Workspace(5, 5)
        val robot = Robot(Position(6, 2, Orientation.N))
        assertThrows(IllegalArgumentException::class.java) {
            workspace.addRobot(robot)
        }
    }
}
