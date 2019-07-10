package com.game.slidingpuzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.game.slidingpuzzle.databinding.PuzzleFragmentBinding

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

    private val viewModel by lazy { ViewModelProviders.of(requireActivity()).get(PuzzleViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PuzzleFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


}
