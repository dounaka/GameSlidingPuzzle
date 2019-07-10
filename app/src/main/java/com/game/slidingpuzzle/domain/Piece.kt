package com.game.slidingpuzzle.domain

data class Piece(val id: Int, var location: Int) {
    fun isSet(): Boolean = id == location
    fun isEmptyPiece() = id == 0

    val locationX: Int
        get() = location % 10

    val locationY: Int
        get() = location / 10
}