package vwdh.domain.valueobject


import org.example.vwdh.domain.valueobject.Orientation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrientationTest {

    @Test
    fun rotateLeftTest() {
        assertEquals(Orientation.W, Orientation.N.left())
    }

    @Test
    fun rotateRightTest() {
        assertEquals(Orientation.E, Orientation.N.right())
    }

    @Test
    fun fullLeftRotationTest() {
        var o = Orientation.N
        repeat(4) { o = o.left() }
        assertEquals(Orientation.N, o)
    }

    @Test
    fun fullRightRotationTest() {
        var o = Orientation.N
        repeat(4) { o = o.right() }
        assertEquals(Orientation.N, o)
    }
}