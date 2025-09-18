package vwdh.domain.valueobject

import org.example.vwdh.domain.model.Robot
import org.example.vwdh.domain.model.Workspace
import org.example.vwdh.domain.valueobject.Orientation
import org.example.vwdh.domain.valueobject.Position

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows

class PositionTest {

    @Test
    fun moveNorthTest() {
        val pos = Position(0, 0, Orientation.N)
        val next = pos.move(5, 5)
        assertEquals(Position(0, 1, Orientation.N), next)
    }

    @Test
    fun moveEastTest() {
        val pos = Position(0, 0, Orientation.E)
        val next = pos.move(5, 5)
        assertEquals(Position(1, 0, Orientation.E), next)
    }

    @Test
    fun moveSouthTest() {
        val pos = Position(0, 1, Orientation.S)
        val next = pos.move(5, 5)
        assertEquals(Position(0, 0, Orientation.S), next)
    }

    @Test
    fun moveWestTest() {
        val pos = Position(1, 0, Orientation.W)
        val next = pos.move(5, 5)
        assertEquals(Position(0, 0, Orientation.W), next)
    }

    fun `robot cannot move outside workspace`() {
        val workspace = Workspace(3, 3)
        val robot = Robot(Position(0, 0, Orientation.W))
        assertThrows(IllegalArgumentException::class.java) {
            robot.executeInstructions("M", 5, 5)
        }
    }
}
