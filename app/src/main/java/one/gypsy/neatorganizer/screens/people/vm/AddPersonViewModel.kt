package one.gypsy.neatorganizer.screens.people.vm

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.interactors.AddPerson
import one.gypsy.neatorganizer.interactors.GetImageBitmap
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.SingleLiveEvent
import one.gypsy.neatorganizer.utils.default
import java.util.*
import javax.inject.Inject

class AddPersonViewModel @Inject constructor(var addPersonUseCase: AddPerson, var getImageBitmapUseCase: GetImageBitmap): ViewModel() {

    private val selectThumbnailPhotoActionRequestCode = 112

    var selectThumbnailPhoto = SingleLiveEvent<Int>()

    var selectedThumbnail= MutableLiveData<Bitmap>()

    var personName = MutableLiveData<String>()

    var finishedAdding = MutableLiveData<Boolean>()

    fun startSelectThumbnailPhotoAction() {
        selectThumbnailPhoto.postValue(selectThumbnailPhotoActionRequestCode)
    }

    fun handleIntentPictureData(data: Uri) {
        getImageBitmapUseCase.invoke(viewModelScope, GetImageBitmap.Params(data)) {
            it.either(::onSelectionFailure, ::onSelectionSuccess)
        }
    }

    fun addPerson() {
        addPersonUseCase.invoke(viewModelScope, AddPerson.Params(Person(personName.value ?: "", 0, Date()))) {
            it.either(::onAdditionFailure, ::onAdditionSuccess)
        }
    }

    private fun onAdditionSuccess(any: Any) {
        finishedAdding.postValue(true)
    }
    private fun onAdditionFailure(failure: Failure) {

    }
    private fun onSelectionFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onSelectionSuccess(bitmap: Bitmap) {
        selectedThumbnail.value = bitmap
    }


}