package com.game.slidingpuzzle.ui

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import com.game.slidingpuzzle.R
import com.game.slidingpuzzle.domain.Piece
import com.game.slidingpuzzle.domain.Puzzle


/**
 * Custom that will displays the puzzle image
 *
 */
class PuzzleView(context: Context, attrs: AttributeSet) : View(context, attrs), GestureDetector.OnGestureListener {
    var textHeight: Float = 0f
    var puzzleSize: Float = 0f
    var pieceSize: Float = 6000f

    var viewWidth: Float = 0f
    var viewHeight: Float = 0f


     var puzzleBitmap: Bitmap
    private lateinit var mDetector: GestureDetectorCompat

    init {
        puzzleBitmap = BitmapFactory.decodeResource(resources, R.drawable.android_games)
        mDetector = GestureDetectorCompat(context, this)
    }

    private val piecePaint = Paint(ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f
        color = ContextCompat.getColor(context, R.color.colorPiece)

        if (textHeight == 0f) {
            textHeight = textSize
        } else {
            textSize = textHeight
        }
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
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        puzzle?.let { pzle ->
            canvas.apply {
                pzle.pieces.values.forEach { piece ->
                    if (piece.isEmptyPiece().not())
                        draw(this, piece)
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

    private fun draw(canvas: Canvas, piece: Piece) {
        if (puzzleSize == 0f || pieceSize == 0f) return
        val locationX = (piece.locationX - 1) * pieceSize
        val locationY = (piece.locationY - 1) * pieceSize

        val bitmapX = (piece.idX - 1) * pieceSize
        val bitmapY = (piece.idY - 1) * pieceSize
        val bitmapSize = (pieceSize - PIECE_PADDING).toInt()
        try {
            val mBitmap = Bitmap.createBitmap(
                puzzleBitmap,
                bitmapX.toInt(), bitmapY.toInt(),
                bitmapSize, bitmapSize
            )
            canvas.drawBitmap(mBitmap, locationX, locationY, null)
        } catch (e: Exception) {
            e.printStackTrace()
            //TODO need to handle the rotation
            // during rotation need to do a new bitmap puzzleBitmap
        }

    }


    companion object {
        const val PIECE_PADDING = 4f
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.d("toucH", "onShowPress ")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d("toucH", "scroll onSingleTapUp")
        return true
    }



    override fun onDown(e: MotionEvent?): Boolean {
        e?.let{
            val locationX = (it.x / pieceSize ).toInt() +1
            val locationY = (it.y / pieceSize ).toInt() +1
            puzzle?.let { pzzle ->
                val piece = pzzle.pieces[ locationX + (10*locationY)]
                if (pzzle.move(piece))
                    invalidate()
            }

        }
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d("toucH", "scroll $distanceX  $distanceY")
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d("toucH", "onFling")
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

}