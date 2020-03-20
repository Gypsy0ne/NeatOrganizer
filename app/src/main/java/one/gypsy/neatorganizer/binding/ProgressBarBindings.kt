package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import com.hsalf.smilerating.BaseRating
import com.hsalf.smilerating.SmileRating

@BindingAdapter("ratingChangeListener")
fun bindRatingBar(ratingBar: SmileRating, listener: SmileRating.OnRatingSelectedListener) {
    ratingBar.apply {
        setSelectedSmile(BaseRating.OKAY, false)
        setOnRatingSelectedListener(listener)
    }
}