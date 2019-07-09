package com.game.slidingpuzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

/**
 *
 * Fragment displays
 * the PuzzleView
 * Controls to reset, start new game, choose a dimension, show progression
 *
 * viewmodel will manage the business logic
 * start new game
 * move piece to empty_piece or alert_error
 *
 */
class PuzzleFragment : Fragment() {

    companion object {
        fun newInstance() = PuzzleFragment()
    }

    private lateinit var viewModel: PuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.puzzle_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PuzzleViewModel::class.java)
    }


}
