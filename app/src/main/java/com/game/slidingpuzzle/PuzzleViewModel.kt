package com.game.slidingpuzzle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.game.slidingpuzzle.domain.Puzzle

class PuzzleViewModel : ViewModel() {
     val puzzle = MutableLiveData<Puzzle>()

    fun startNewGame(dim:Int) {
        val pzle = Puzzle()
        pzle.startNewGame(4)
        puzzle.postValue(pzle)
    }
}
