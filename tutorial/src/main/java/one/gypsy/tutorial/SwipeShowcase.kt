package one.gypsy.tutorial

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.shape.ShapeType

internal class SwipeShowcase {

    materialIntro(show = true /* if you want to show miv instantly */)
    {
        focusType = Focus.MINIMUM
        shapeType = ShapeType.RECTANGLE

        isFadeInAnimationEnabled = true
        isFadeOutAnimationEnabled = true
        fadeAnimationDurationMillis = 300

        focusGravity = FocusGravity.CENTER

        padding = 16 // in px

        dismissOnTouch = false

        isInfoEnabled = true
        infoText = "Hello this is help message"
        infoTextColor = Color.BLACK
        infoTextSize = 18f
        infoTextAlignment = View.TEXT_ALIGNMENT_CENTER
        infoTextTypeface = Typeface.DEFAULT_BOLD
        infoCardBackgroundColor = Color.WHITE

        isHelpIconEnabled = true
//                    helpIconResource = R.drawable.your_icon
//                    helpIconDrawable = yourDrawable
//                helpIconColor = Color.RED

//                    infoCustomView = yourViewHere
//                    infoCustomViewRes = R.layout.your_custom_view_here

        isDotViewEnabled = false
//                isDotAnimationEnabled = true
//                dotIconColor = Color.WHITE

//                    viewId = "unique_id" // or automatically picked from view's tag
        targetView = firstHolderView

        isPerformClick = false

        showOnlyOnce = false
        userClickAsDisplayed = true
    }

}