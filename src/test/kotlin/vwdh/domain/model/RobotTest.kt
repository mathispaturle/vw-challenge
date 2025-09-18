package vwdh.domain.model


import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.valueobject.Orientation
import org.example.vwdh.domain.valueobject.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun executeFirstExampleTest() {
        val robot = Robot(Position(1, 2, Orientation.N))
        robot.executeInstructions("LMLMLMLMM", 5, 5)
        assertEquals(Position(1, 3, Orientation.N), robot.position)
    }

    @Test
    fun executeSecondExampleTest() {
        val robot = Robot(Position(3, 3, Orientation.E))
        robot.executeInstructions("MMRMMRMRRM", 5, 5)
        assertEquals(Position(5, 1, Orientation.E), robot.position)
    }
}