package one.gypsy.neatorganizer.core.binding

import androidx.databinding.BindingAdapter
import com.thebluealliance.spectrum.SpectrumPalette

@BindingAdapter("onColorChanged")
fun bindOnColorChangedListener(
    view: SpectrumPalette,
    listener: SpectrumPalette.OnColorSelectedListener
) = view.setOnColorSelectedListener(listener)
