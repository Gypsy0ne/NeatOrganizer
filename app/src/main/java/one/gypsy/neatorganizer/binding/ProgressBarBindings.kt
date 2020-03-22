package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import com.hsalf.smilerating.BaseRating
import com.hsalf.smilerating.SmileRating

@BindingAdapter("ratingChangeListener")
fun setRatingBarListener(ratingBar: SmileRating, listener: SmileRating.OnRatingSelectedListener) {
    ratingBar.apply {
        setOnRatingSelectedListener(listener)
        setSelectedSmile(BaseRating.OKAY, false)
    }
}