package com.game.slidingpuzzle

import com.game.slidingpuzzle.domain.Puzzle
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PuzzleTest {
    @Test
    fun puzzle_buildMatrice() {

        val puzzle = Puzzle()

        puzzle.startNewGame(3)
        assertEquals(puzzle.size(), 3 * 3)
        assertNotNull(puzzle.emptyPiece)
        assertFalse(puzzle.isSet())

        puzzle.startNewGame(9)
        assertEquals(puzzle.size(), 9 * 9)
        assertNotNull(puzzle.emptyPiece)
        assertFalse(puzzle.isSet())
    }


}
