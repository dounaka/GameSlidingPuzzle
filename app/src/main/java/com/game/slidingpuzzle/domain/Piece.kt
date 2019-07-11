package com.game.slidingpuzzle.domain

data class Piece(val id: Int, var location: Int) {

    companion object {
        const val MAX_DIMENSION = 9
    }

    fun isSet(): Boolean = id == location
    fun isEmptyPiece() = id == 0
    val idX: Int
        get() = id % 10

    val idY: Int
        get() = id / 10

    val locationX: Int
        get() = location % 10

    val locationY: Int
        get() = location / 10

    val locationTop
        get() = location - 10

    val locationLeft
        get() = location - 1

    val locationRight
        get() = location + 1

    val locationBottom
        get() = location + 10

}