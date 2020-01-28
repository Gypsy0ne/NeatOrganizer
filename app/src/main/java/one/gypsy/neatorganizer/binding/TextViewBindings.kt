package one.gypsy.neatorganizer.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dateText")
fun setDateText(view: TextView, date: Date) {
    view.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}