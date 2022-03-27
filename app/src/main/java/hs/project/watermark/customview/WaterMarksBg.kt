package hs.project.watermark.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.IntRange

class WaterMarksBg(
    context: Context, labels: List<String>, angle: Int, fontSize: Int
): Drawable() {

    private val paint = Paint()
    private var labels: List<String> = labels
    private var context: Context = context
    private var angle = angle
    private var fontSize = fontSize


    override fun draw(canvas: Canvas) {
        val width = bounds.right
        val height = bounds.bottom * 2

        canvas.drawColor(Color.parseColor("#40F3F5F9"))
        paint.color = Color.parseColor("#000000")

        paint.isAntiAlias = true
        paint.textSize = sp2px(context, fontSize.toFloat()).toFloat()
        canvas.save()
        canvas.rotate(angle.toFloat())
        val textWidth = paint.measureText(labels[0])
        var index = 0
        var positionY = height / 10

        while (positionY <= height) {
            val fromX = -width + index++ % 2 * textWidth
            var positionX = fromX
            while (positionX <= width * 10) {
                var spacing = 0
                for (label in labels) {
                    canvas.drawText(label, positionX, (positionY + spacing).toFloat(), paint)
                    spacing += 50
                }
                positionX += textWidth * 2
            }
            positionY += height / 10 + 50
        }
        canvas.restore()
    }

    override fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int) {}

    override fun setColorFilter(p0: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}