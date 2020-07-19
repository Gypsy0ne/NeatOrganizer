package one.gypsy.neatorganizer.presentation.people.vm

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polyak.iconswitch.IconSwitch
import one.gypsy.neatorganizer.domain.dto.people.Person
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.people.AddPerson
import one.gypsy.neatorganizer.domain.interactors.people.GetImageBitmap
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.SingleLiveEvent
import one.gypsy.neatorganizer.utils.extensions.default
import one.gypsy.neatorganizer.utils.extensions.orNow
import java.util.*


class AddPersonViewModel(
    private val addPersonUseCase: AddPerson,
    private val getImageBitmapUseCase: GetImageBitmap
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

    private val _birthDate = MutableLiveData<Date>().default(Date())
    val birthDate: LiveData<Date>
        get() = _birthDate

    private val _sex = MutableLiveData<Person.Sex>().default(Person.Sex.MALE)
    val sex: LiveData<Person.Sex>
        get() = _sex

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
                PersonEntry(
                    name = personName.value.orEmpty(),
                    sex = sex.value ?: Person.Sex.MALE,
                    photoThumbnail = selectedThumbnail.value,
                    lastInteraction = 0,
                    dateOfBirth = birthDate.value.orNow()
                )
            )
        ) {
            it.either(::onAdditionFailure, ::onAdditionSuccess)
        }
    }

    private fun onAdditionSuccess(unit: Long) {
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

    fun onSexChanged(sex: IconSwitch.Checked) {
        _sex.postValue(
            when (sex) {
                IconSwitch.Checked.LEFT -> Person.Sex.MALE
                else -> Person.Sex.FEMALE
            }
        )
    }

    fun onBirthDateChanged(newDate: Date) {
        _birthDate.postValue(newDate)
    }
}
