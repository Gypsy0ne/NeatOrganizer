package one.gypsy.neatorganizer.presentation.people.vm

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.Person
import one.gypsy.neatorganizer.domain.interactors.AddPerson
import one.gypsy.neatorganizer.domain.interactors.GetImageBitmap
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.SingleLiveEvent
import java.util.*
import javax.inject.Inject
import android.widget.DatePicker
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.time.LocalDate


class AddPersonViewModel @Inject constructor(
    var addPersonUseCase: AddPerson,
    var getImageBitmapUseCase: GetImageBitmap
) : ViewModel() {

    private val selectThumbnailPhotoActionRequestCode = 112

    private val _selectThumbnailPhoto = SingleLiveEvent<Int>()
    val selectThumbnailPhoto: LiveData<Int>
        get() = _selectThumbnailPhoto

    private val _selectedThumbnail = MutableLiveData<Bitmap>()
    val selectedThumbnail: LiveData<Bitmap>
        get() = _selectedThumbnail

    val personName = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    private val _birthDay = MutableLiveData<Int>()
    val birthDay: LiveData<Int>
        get() = _birthDay

    private val _birthMonth = MutableLiveData<Int>()
    val birthMonth: LiveData<Int>
        get() = _birthMonth

    private val _birthYear = MutableLiveData<Int>()
    val birthYear: LiveData<Int>
        get() = _birthYear

    fun startSelectThumbnailPhotoAction() {
        _selectThumbnailPhoto.postValue(selectThumbnailPhotoActionRequestCode)
    }

    fun handleIntentPictureData(data: Uri) {
        getImageBitmapUseCase.invoke(viewModelScope, GetImageBitmap.Params(data)) {
            it.either(::onSelectionFailure, ::onSelectionSuccess)
        }
    }

    fun addPerson() {
        addPersonUseCase.invoke(
            viewModelScope, AddPerson.Params(
                Person(
                    0,
                    personName.value ?: "",
                    selectedThumbnail.value,
                    0,
                    Date()
                )
            )
        ) {
            it.either(::onAdditionFailure, ::onAdditionSuccess)
        }
    }

    private fun onAdditionSuccess(unit: Unit) {
        _finishedAdding.postValue(true)
    }

    private fun onAdditionFailure(failure: Failure) {

    }

    private fun onSelectionFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onSelectionSuccess(bitmap: Bitmap) {
        _selectedThumbnail.value = bitmap
    }


    fun onBirthDateChanged(year: Int, monthOfYear: Int, dayOfMonth: Int) {
    }
}
