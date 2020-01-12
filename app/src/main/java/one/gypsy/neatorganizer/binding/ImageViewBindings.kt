package one.gypsy.neatorganizer.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageBitmap", "placeholder"], requireAll = false)
fun setImage(view: ImageView, imageBitmap: Bitmap?, placeholder: Drawable) {
    Glide.with(view.context)
        .load(imageBitmap ?: placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(view)
}
