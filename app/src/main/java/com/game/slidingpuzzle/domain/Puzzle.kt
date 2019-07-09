package com.game.slidingpuzzle.domain

class Puzzle() {

    val pieces = hashMapOf<Int, Piece>()

    var emptyPiece: Piece? = null

    fun startNewGame(dimension: Int) {
        if (dimension > 9 || dimension < 2) throw IllegalArgumentException(ERROR_INVALID_DIMENSION)
        if (pieces.isNotEmpty()) pieces.clear()
        val matrice = mutableListOf<Int>()
        for (y in 1..dimension)
            for (x in 1..dimension)
                matrice.add(y * 10 + x)
        val locations = matrice.shuffled()
        locations.forEachIndexed { index, location ->
            // first shuffled is the empty piece
            pieces.put(location, Piece(if (index == 0) 0 else matrice[index], location))
            if (index ==0)
                emptyPiece = pieces[location]
        }
    }


    fun isSet(): Boolean {
        if (pieces.isEmpty()) throw IllegalArgumentException(ERROR_GAME_NOT_STARTED)
        pieces.values.forEach { piece ->
            if (piece.isSet().not()) return false
        }
        return true
    }

    fun solve() {
        TODO("not implemented")
    }

    fun size(): Int = pieces.size

    companion object {
        const val ERROR_INVALID_DIMENSION = "Error - dimension should be a value between 2 and 9"
        const val ERROR_GAME_NOT_STARTED = "Error - game not started"
    }
}