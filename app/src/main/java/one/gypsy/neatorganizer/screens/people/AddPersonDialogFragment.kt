package one.gypsy.neatorganizer.screens.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_add_person.*
import one.gypsy.neatorganizer.R

class AddPersonDialogFragment :BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_add_person, container, false)
    }

    override fun onStart() {
        super.onStart()
    setUpThumbnailSlot()
    }

    private fun setUpThumbnailSlot() {
            Glide.with(image_view_dialog_fragment_add_person_thumbnail.context).load(R.drawable.ic_add_a_photo_black_24dp).apply(
                RequestOptions.circleCropTransform()).into(image_view_dialog_fragment_add_person_thumbnail);
    }

}