package one.gypsy.neatorganizer.presentation.people.vm

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.interactors.AddPerson
import one.gypsy.neatorganizer.interactors.GetImageBitmap
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.SingleLiveEvent
import java.util.*
import javax.inject.Inject
//TODO Change the way of exposing LiveData https://gist.github.com/humblehacker/0eb6458b1df6cf3049e031f36f0615f5
class AddPersonViewModel @Inject constructor(var addPersonUseCase: AddPerson, var getImageBitmapUseCase: GetImageBitmap): ViewModel() {

    private val selectThumbnailPhotoActionRequestCode = 112

    var selectThumbnailPhoto = SingleLiveEvent<Int>()

    var selectedThumbnail: LiveData<Bitmap> = MutableLiveData<Bitmap>()

    var personName = MutableLiveData<String>()

    var finishedAdding: LiveData<Boolean>  = MutableLiveData<Boolean>()

    fun startSelectThumbnailPhotoAction() {
        selectThumbnailPhoto.postValue(selectThumbnailPhotoActionRequestCode)
    }

    fun handleIntentPictureData(data: Uri) {
        getImageBitmapUseCase.invoke(viewModelScope, GetImageBitmap.Params(data)) {
            it.either(::onSelectionFailure, ::onSelectionSuccess)
        }
    }

    fun addPerson() {
        addPersonUseCase.invoke(viewModelScope, AddPerson.Params(Person(0,personName.value ?: "", selectedThumbnail.value, 0, Date()))) {
            it.either(::onAdditionFailure, ::onAdditionSuccess)
        }
    }

    private fun onAdditionSuccess(unit: Unit) {
        (finishedAdding as MutableLiveData).postValue(true)
    }

    private fun onAdditionFailure(failure: Failure) {

    }
    private fun onSelectionFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onSelectionSuccess(bitmap: Bitmap) {
        (selectedThumbnail as MutableLiveData).value = bitmap
    }


}
