package com.game.slidingpuzzle.ui

import androidx.databinding.BindingAdapter
import com.game.slidingpuzzle.domain.Puzzle


class PuzzleBindingAdapter {

    companion object {

        @BindingAdapter("drawPuzzle")
        @JvmStatic
        fun setPuzzle(puzzleView: PuzzleView, puzzle: Puzzle?) {
             puzzleView.drawPuzzle(puzzle)
        }

    }




}