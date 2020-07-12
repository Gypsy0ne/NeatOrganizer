package one.gypsy.neatorganizer.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import one.gypsy.neatorganizer.R

@BindingAdapter(value = ["imageBitmap", "placeholder"], requireAll = false)
fun setImage(view: ImageView, imageBitmap: Bitmap?, placeholder: Drawable) {
    Glide.with(view.context)
        .load(imageBitmap ?: placeholder)
        .transform(CenterCrop(), RoundedCorners(24))
        .into(view)
}

@BindingAdapter("interactionRatingIcon")
fun setInteractionRatingImage(view: ImageView, rating: Int) {
    view.setImageResource(
        when (rating) {
            1 -> R.drawable.ic_terrible_32dp
            2 -> R.drawable.ic_bad_32dp
            4 -> R.drawable.ic_good_32dp
            5 -> R.drawable.ic_great_32dp
            else -> R.drawable.ic_okay_32dp
        }
    )
}

