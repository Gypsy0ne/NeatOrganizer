package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import com.polyak.iconswitch.IconSwitch


@BindingAdapter("checkedChangeListener")
fun setOnCheckedChangeListener(view: IconSwitch, listener: IconSwitch.CheckedChangeListener) {
    view.setCheckedChangeListener(listener)
}

