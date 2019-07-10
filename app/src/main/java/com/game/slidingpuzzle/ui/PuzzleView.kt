package com.game.slidingpuzzle.ui

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.game.slidingpuzzle.R
import com.game.slidingpuzzle.domain.Piece
import com.game.slidingpuzzle.domain.Puzzle


/**
 * Custom that will displays the puzzle image
 *
 */
class PuzzleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var textHeight: Float = 0f
    var puzzleSize: Float = 0f
    var pieceSize: Float = 0f

    var viewWidth: Float = 0f
    var viewHeight: Float = 0f
    private val piecePaint = Paint(ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f
        color = ContextCompat.getColor(context, R.color.colorPiece)

        if (textHeight == 0f) {
            textHeight = textSize
        } else {
            textSize = textHeight
        }
    }

    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textSize = textHeight
    }

    private val shadowPaint = Paint(0).apply {
        color = 0x101010
        maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.NORMAL)
    }
    var puzzle: Puzzle? = null


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        var xpad = (paddingLeft + paddingRight).toFloat()
        val ypad = (paddingTop + paddingBottom).toFloat()
        viewWidth = w.toFloat() - xpad
        viewHeight = h.toFloat() - ypad
        puzzleSize = Math.min(viewWidth, viewHeight)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        this.setMeasuredDimension(parentWidth, parentHeight)
        viewWidth = parentWidth.toFloat()
        viewHeight = parentHeight.toFloat()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        puzzle?.let { pzle ->
            canvas.apply {
                pzle.pieces.values.forEach { piece ->
                    if (piece.isEmptyPiece().not())
                        drawRect(convertToRectangle(piece), piecePaint)
                }
            }
        }
    }

    fun drawPuzzle(pzle: Puzzle?) {
        pzle?.let {
            this.puzzle = it
            pieceSize = puzzleSize / it.dimension
        }
        invalidate()


    }

    private fun convertToRectangle(piece: Piece): RectF {
        val startX = (piece.locationX - 1) * pieceSize
        val startY = (piece.locationY - 1) * pieceSize
        return RectF(startX, startY, (startX + pieceSize) - 2, (startY + pieceSize) - 2)

    }

}