package one.gypsy.neatorganizer.presentation.people.vm

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polyak.iconswitch.IconSwitch
import one.gypsy.neatorganizer.domain.dto.Person
import one.gypsy.neatorganizer.domain.interactors.AddPerson
import one.gypsy.neatorganizer.domain.interactors.GetImageBitmap
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.SingleLiveEvent
import one.gypsy.neatorganizer.utils.default
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


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

    private val _birthDay = MutableLiveData<Int>().default(1)
    val birthDay: LiveData<Int>
        get() = _birthDay

    private val _birthMonth = MutableLiveData<Int>().default(0)
    val birthMonth: LiveData<Int>
        get() = _birthMonth

    private val _birthYear = MutableLiveData<Int>().default(2000)
    val birthYear: LiveData<Int>
        get() = _birthYear

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
                Person(
                    0,
                    personName.value ?: "",
                    sex.value ?: Person.Sex.MALE,
                    selectedThumbnail.value,
                    0,
                    getBirthDateFromFields()
                )
            )
        ) {
            it.either(::onAdditionFailure, ::onAdditionSuccess)
        }
    }

    private fun getBirthDateFromFields() = GregorianCalendar(birthYear.value ?: 2000, birthMonth.value ?: 0, birthDay.value ?: 0).time

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

    fun onSexChanged(sex: IconSwitch.Checked) {
        _sex.postValue(when(sex) {
            IconSwitch.Checked.LEFT -> Person.Sex.MALE
            else -> Person.Sex.FEMALE
        })
    }


    fun onBirthDateChanged(year: Int, month: Int, dayOfMonth: Int) {
        _birthYear.value = year
        _birthMonth.value = month
        _birthDay.value = dayOfMonth
    }
}
